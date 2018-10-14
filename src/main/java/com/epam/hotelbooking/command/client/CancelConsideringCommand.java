package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CancelConsideringCommand implements Command {

    private final ApplicationService service;

    public CancelConsideringCommand(ApplicationService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String applicationIdParam = request.getParameter(Bill.APPLICATION_ID);
        Long applicationId = Long.parseLong(applicationIdParam);

        Optional<Application> optionalApplication = service.findApplicationById(applicationId);

        if (!optionalApplication.isPresent()) {
            throw new ServiceException(Application.TABLE_NAME + " with id " + applicationId + CommonConstants.NOT_EXIST);
        }

        Application application = optionalApplication.get();
        application.setApplicationStatus(ApplicationStatus.CANCELED);
        service.saveApplication(application);

        return RedirectConstants.APPLICATION_HISTORY_REDIRECT;
    }
}
