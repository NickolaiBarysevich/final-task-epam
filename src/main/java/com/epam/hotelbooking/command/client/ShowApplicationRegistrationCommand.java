package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is show command which only returns jsp page.
 *
 * @author Nickolai Barysevich.
 */
public class ShowApplicationRegistrationCommand implements Command {

    /**
     * Return applicationRegistration.jsp.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return applicationRegistration.jsp.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspConstants.APPLICATION_REGISTRATION_JSP;
    }
}
