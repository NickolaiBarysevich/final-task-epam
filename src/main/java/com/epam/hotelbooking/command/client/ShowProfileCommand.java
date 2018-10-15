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
public class ShowProfileCommand implements Command {

    /**
     * Return profile.jsp.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return profile.jsp.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspConstants.PROFILE_JSP;
    }
}
