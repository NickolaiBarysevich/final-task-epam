package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExitCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return JspConstants.LOGIN_JSP;
    }
}
