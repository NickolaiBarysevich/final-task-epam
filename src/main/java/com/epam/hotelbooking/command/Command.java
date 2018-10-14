package com.epam.hotelbooking.command;

import com.epam.hotelbooking.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
