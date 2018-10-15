package com.epam.hotelbooking.command;

import com.epam.hotelbooking.command.factory.CommandFactory;
import com.epam.hotelbooking.controller.Controller;
import com.epam.hotelbooking.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Invokes into {@link Controller} and define what actions should
 * be made. Controller looks at {@link HttpServletRequest} parameter
 * {@code command} and then sends it to {@link CommandFactory} where the
 * concrete command creates.
 *
 * @author Nickolai Barysevich.
 */
public interface Command {

    /**
     * Provides the main logic of the command. By getting
     * parameters and setting attributes to the {@code request}.
     *
     * @param request http request that was got from browser
     * @param response http response that should be sent to browser
     * @return the jsp page or redirect path for servlet.
     * @throws ServiceException if some service error has occurred.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
