package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
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

/**
 * Finds application and represents it on jsp.
 *
 * @author Nickolai Barysevich.
 */
public class ApplicationInfoCommand implements Command {

    private final ApplicationBillDtoService service;

    public ApplicationInfoCommand(ApplicationBillDtoService service) {
        this.service = service;
    }

    /**
     * Finds application and put information about it
     * into {@code request}.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return applicationInfo.jsp
     * @throws ServiceException if some service error has occurred
     *                          or {@code optionApplication} is {Optional.empty}.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String applicationIdValue = request.getParameter(Bill.APPLICATION_ID);
        Long applicationId = Long.parseLong(applicationIdValue);

        Optional<ApplicationBillDto> optionalApplication = service.findApplicationBillDto(applicationId);

        if (optionalApplication.isPresent()) {

            ApplicationBillDto application = optionalApplication.get();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.TABLE_NAME);

            if (service.checkApplicationBelongsToUser(application, user)) {
                request.setAttribute(Application.TABLE_NAME, application);
                return JspConstants.APPLICATION_INFO_JSP;
            }
        }

        throw new ServiceException("application" + CommonConstants.NOT_EXIST);

    }

}
