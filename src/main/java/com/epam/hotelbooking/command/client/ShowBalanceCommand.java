package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBalanceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspConstants.BALANCE_JSP;
    }
}
