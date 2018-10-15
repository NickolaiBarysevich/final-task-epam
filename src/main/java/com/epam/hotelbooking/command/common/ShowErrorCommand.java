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
public class ShowErrorCommand implements Command {

    /**
     * Return error.jsp.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return error.jsp.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspConstants.ERROR_JSP;
    }
}
