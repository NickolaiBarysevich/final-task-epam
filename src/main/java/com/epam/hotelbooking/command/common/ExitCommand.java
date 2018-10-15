package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command realises the logic of button
 * "sign out".
 *
 * @author Nickolai Barysevich
 */
public class ExitCommand implements Command {

    /**
     * Invalidates {@link HttpSession} and returns
     * login.jsp.
     *
     * @param request http request that was got from browser
     * @param response http response that should be sent to browser
     * @return login.jsp.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return JspConstants.LOGIN_JSP;
    }
}
