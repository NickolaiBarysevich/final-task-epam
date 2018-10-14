package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRegistrationCommand implements Command {

    private static final String REGISTRATION_ERROR = "registrationError";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String registrationError = request.getParameter(REGISTRATION_ERROR);
        request.setAttribute(REGISTRATION_ERROR, registrationError);

        return JspConstants.REGISTRATION_JSP;
    }


}
