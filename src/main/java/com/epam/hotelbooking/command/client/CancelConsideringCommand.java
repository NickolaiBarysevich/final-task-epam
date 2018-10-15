package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Allows user to cancel considering applications.
 *
 * @author Nickolai Barysevich.
 */
public class CancelConsideringCommand implements Command {

    private static final String MESSAGE_CANT_BE_CANCELED = "message=profile.cantBeCanceled";

    private final ApplicationService service;

    public CancelConsideringCommand(ApplicationService service) {
        this.service = service;
    }

    /**
     * Cancels considering application.
     *
     * @param request http request that was got from browser
     * @param response http response that should be sent to browser
     * @return redirect path to applicationHistory.jsp
     * @throws ServiceException if some service error has occurred.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String applicationIdParam = request.getParameter(Bill.APPLICATION_ID);
        Long applicationId = Long.parseLong(applicationIdParam);

        if (!service.isCancelable(applicationId)) {
            return RedirectConstants.APPLICATION_HISTORY_REDIRECT + MESSAGE_CANT_BE_CANCELED;
        }

        service.markApplication(applicationId, ApplicationStatus.CANCELED);

        return RedirectConstants.APPLICATION_HISTORY_REDIRECT;
    }
}
