package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationBillDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowInfoCommand implements Command {

    private final ApplicationBillDtoService service;

    public ShowInfoCommand(ApplicationBillDtoService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String applicationIdValue = request.getParameter(Bill.APPLICATION_ID);
        Long applicationId = Long.parseLong(applicationIdValue);

        Optional<ApplicationBillDto> optionalApplication = service.findApplicationDto(applicationId);

        if (optionalApplication.isPresent()) {

            ApplicationBillDto application = optionalApplication.get();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.TABLE_NAME);

            if (service.checkApplicationBelongsToUser(application, user)) {
                request.setAttribute(Application.TABLE_NAME, application);
                return JspConstants.APPLICATION_INFO_JSP;
            }
        }

        return JspConstants.NOT_FOUND_JSP;

    }

}
