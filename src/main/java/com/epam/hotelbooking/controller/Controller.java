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

/**
 * Controls gotten requests, define by which method
 * it should handled. This class extends only 4 methods
 * of the superclass: {@link #doGet(HttpServletRequest, HttpServletResponse)},
 * {@link #doPost(HttpServletRequest, HttpServletResponse)}, {@link #init()},
 * {@link #destroy()}.
 *
 * @author Nickolai Barysevich.
 */
public class Controller extends HttpServlet {

    private final Logger logger = Logger.getLogger(Controller.class);

    /**
     * When servlet was created starts a timer
     * which finds expired applications. Checks for
     * it every day at 12 PM.
     */
    @Override
    public void init() {
        ApplicationTimer timer = new ApplicationTimer();
        timer.startTimer();
    }

    /**
     * Handles requests that was got by GET method.
     * Does forward to the needed jsp page.
     *
     * @param req request gotten from browser
     * @param resp response that was sent to browser
     * @throws ServletException if some servlet exception has occurred
     * @throws IOException if som IO exception has occurred
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = getPage(req, resp, JspConstants.ERROR_JSP);
        dispatch(req, resp, page);
    }

    /**
     * Handles requests that was got by POST method.
     * Does redirect to the needed jsp page.
     *
     * @param req request gotten from browser
     * @param resp response that was sent to browser
     * @throws IOException if som IO exception has occurred
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = getPage(req, resp, RedirectConstants.ERROR_REDIRECT);
        resp.sendRedirect(page);
    }

    /**
     * Return the page that needs to be
     * viewed in the browser.
     *
     * @param req request gotten from browser
     * @param resp response that was sent to browser
     * @param errorPage page that should be viewed if service error has occured
     * @return page that needs to be viewed in the browser.
     */
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

    /**
     * Defines and executes concrete command
     * that client wants to process.
     *
     * @param req request gotten from browser
     * @param resp response that was sent to browser
     * @return concrete jsp page or redirect path
     * @throws ServiceException if some service error has occurred.
     */
    private String processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String command = req.getParameter(CommandConstants.COMMAND);
        if (command == null || command.isEmpty()) {
            return JspConstants.LOGIN_JSP;
        }
        CommandFactory commandFactory = new CommandFactory();
        Command currentCommand = commandFactory.create(command);
        return currentCommand.execute(req, resp);
    }

    /**
     * Gets request dispatcher and making forward
     * to the jsp page.
     *
     * @param req request gotten from browser
     * @param resp response that was sent to browser
     * @param page the page that must be forwarded.
     * @throws ServletException if some servlet exception has occurred
     * @throws IOException if som IO exception has occurred
     */
    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    /**
     * Invokes on the last step of the servlet
     * lifecycle.
     * Closes all connections in the {@link ConnectionPool}.
     */
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
