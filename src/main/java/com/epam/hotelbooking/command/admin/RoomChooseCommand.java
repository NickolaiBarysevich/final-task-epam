package com.epam.hotelbooking.command.admin;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.entity.dto.RoomDto;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationDtoService;
import com.epam.hotelbooking.service.RoomDtoService;
import com.epam.hotelbooking.util.PaginationHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class RoomChooseCommand implements Command {

    private static final int RECORDS_PER_PAGE = 13;

    private static final String IS_ASSIGNED_PARAM = "management.isAssigned";
    private static final String NOT_SELECTED_PARAM = "management.notSelected";
    private static final String MANAGEMENT_ERROR = "managementError";
    private static final String CANCELED_APPLICATION_PARAM = "management.canceledApplication";

    private RoomDtoService roomDtoService;
    private ApplicationDtoService applicationDtoService;

    public RoomChooseCommand(RoomDtoService roomDtoService, ApplicationDtoService applicationDtoService) {
        this.roomDtoService = roomDtoService;
        this.applicationDtoService = applicationDtoService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String applicationIdValue = request.getParameter(Bill.APPLICATION_ID);
        if (applicationIdValue == null || applicationIdValue.isEmpty()) {
            return formError(request, NOT_SELECTED_PARAM);
        }

        Long applicationId = Long.parseLong(applicationIdValue);

        if (applicationDtoService.isCanceled(applicationId)) {
            return formError(request, CANCELED_APPLICATION_PARAM);
        }

        Optional<ApplicationDto> optionalApplication = applicationDtoService.findApplicationById(applicationId);

        if (!optionalApplication.isPresent()) {
            throw new ServiceException(Application.TABLE_NAME + " with id " + applicationId + CommonConstants.NOT_EXIST);
        }

        ApplicationDto application = optionalApplication.get();

        if (application.getRoomId() != null) {
            return formError(request, IS_ASSIGNED_PARAM);
        }

        String sortParam = request.getParameter(CommonConstants.SORT_PARAM);
        List<RoomDto> roomList = roomDtoService.getRoomList(sortParam);
        PaginationHelper<RoomDto> paginationHelper = new PaginationHelper<>(roomList, RECORDS_PER_PAGE);

        int page = paginationHelper.findPage(request);

        request.setAttribute(Bill.APPLICATION_ID, applicationIdValue);
        request.setAttribute(CommonConstants.ROOM_LIST_ATTRIBUTE, paginationHelper.getPage(page));
        request.setAttribute(CommonConstants.NUMBER_OF_PAGES_ATTRIBUTE, paginationHelper.getNumberOfPages());
        request.setAttribute(CommonConstants.CURRENT_PAGE_ATTRIBUTE, page);
        request.setAttribute(Application.TABLE_NAME, application);
        request.setAttribute(CommonConstants.SORT_PARAM, sortParam);

        return JspConstants.ROOM_CHOOSE_JSP;

    }

    private String formError(HttpServletRequest request, String errorMessage) throws ServiceException {
        request.setAttribute(MANAGEMENT_ERROR, errorMessage);
        request.setAttribute(CommonConstants.APPLICATIONS_ATTRIBUTE, applicationDtoService.getApplications(null));
        return JspConstants.MANAGEMENT_JSP;
    }

}
