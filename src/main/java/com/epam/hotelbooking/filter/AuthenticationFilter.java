package com.epam.hotelbooking.filter;

import com.epam.hotelbooking.command.CommandConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Checks between requests is user is authorised.
 *
 * @author Nickolai Barysevich
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        if (session.getAttribute(User.TABLE_NAME) == null) {

            String command = request.getParameter(CommandConstants.COMMAND);
            if (notAllowed(command)) {

                HttpServletResponse httpResponse = (HttpServletResponse) response;

                httpResponse.sendRedirect(RedirectConstants.SHOW_LOGIN_REDIRECT);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Define is the command not allowed for
     * unauthorised user.
     *
     * @param command command to be checked.
     * @return true if command not allowed.
     */
    private boolean notAllowed(String command) {
        return !CommandConstants.SHOW_LOGIN.equals(command)
                && !CommandConstants.LOGIN.equals(command)
                && !CommandConstants.SHOW_REGISTRATION.equals(command)
                && !CommandConstants.REGISTRATION.equals(command)
                && !CommandConstants.SHOW_REGISTRATION.equals(command);
    }

    @Override
    public void destroy() {

    }
}
