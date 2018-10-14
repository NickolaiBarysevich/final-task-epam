package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowLoginCommand implements Command {

    private static final String LOGIN_ERROR_PARAM = "loginError";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String loginError = request.getParameter(LOGIN_ERROR_PARAM);
        request.setAttribute(LOGIN_ERROR_PARAM, loginError);

        return JspConstants.LOGIN_JSP;
    }
}
