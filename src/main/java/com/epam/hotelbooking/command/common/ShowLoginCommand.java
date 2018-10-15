package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is show command which only returns jsp page.
 *
 * @author Nickolai Barysevich.
 */
public class ShowLoginCommand implements Command {

    private static final String LOGIN_ERROR_PARAM = "loginError";

    /**
     * Specifies message parameters and return login.jsp.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return login.jsp.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String loginError = request.getParameter(LOGIN_ERROR_PARAM);
        request.setAttribute(LOGIN_ERROR_PARAM, loginError);

        return JspConstants.LOGIN_JSP;
    }
}
