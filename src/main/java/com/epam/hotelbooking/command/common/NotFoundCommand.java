package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFoundCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return JspConstants.NOT_FOUND_JSP;
    }
}
