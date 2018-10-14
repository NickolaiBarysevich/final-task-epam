package com.epam.hotelbooking.filter;

import com.epam.hotelbooking.command.CommandConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorisationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        UserRole role = (UserRole) session.getAttribute(User.ROLE);

        if (role != null) {

            HttpServletResponse httpResponse = (HttpServletResponse) response;

            String redirectPage = getRedirectPage(httpRequest, role);

            if (redirectPage != null) {
                httpResponse.sendRedirect(redirectPage);
                return;
            }
        }


        chain.doFilter(request, response);
    }

    private String getRedirectPage(HttpServletRequest httpRequest, UserRole role) {
        String redirectPage = null;
        String command = httpRequest.getParameter(CommandConstants.COMMAND);

        if (role == UserRole.CLIENT && isAdminCommand(command)) {
            redirectPage = RedirectConstants.HOME_REDIRECT;
        } else if (role == UserRole.ADMINISTRATOR
                && !isAdminCommand(command)
                && !CommandConstants.EXIT.equals(command)) {
            redirectPage = RedirectConstants.MANAGEMENT_REDIRECT;
        }
        return redirectPage;
    }

    private boolean isAdminCommand(String command) {
        return CommandConstants.ASSIGN_ROOM.equals(command)
                || CommandConstants.MANAGEMENT.equals(command)
                || CommandConstants.ROOM_CHOOSE.equals(command);
    }

    @Override
    public void destroy() {

    }
}
