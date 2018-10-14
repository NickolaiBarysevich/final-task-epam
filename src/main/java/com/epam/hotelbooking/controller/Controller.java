package com.epam.hotelbooking.controller;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandConstants;
import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.command.factory.CommandFactory;
import com.epam.hotelbooking.dao.connection.ConnectionPool;
import com.epam.hotelbooking.exception.ConnectionException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.task.ApplicationTimer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final Logger logger = Logger.getLogger(Controller.class);

    @Override
    public void init() {
        ApplicationTimer timer = new ApplicationTimer();
        timer.startTimer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = getPage(req, resp, JspConstants.ERROR_JSP);
        dispatch(req, resp, page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = getPage(req, resp, RedirectConstants.ERROR_REDIRECT);
        resp.sendRedirect(page);
    }

    private String getPage(HttpServletRequest req, HttpServletResponse resp, String errorPage) {
        String page;
        try {
            page = processCommand(req, resp);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            page = errorPage;
        }
        return page;
    }

    private String processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String command = req.getParameter(CommandConstants.COMMAND);
        if (command == null || command.isEmpty()) {
            return JspConstants.LOGIN_JSP;
        }
        CommandFactory commandFactory = new CommandFactory();
        Command currentCommand = commandFactory.create(command);
        return currentCommand.execute(req, resp);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }


    @Override
    public void destroy() {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            pool.closeConnections();
        } catch (ConnectionException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
