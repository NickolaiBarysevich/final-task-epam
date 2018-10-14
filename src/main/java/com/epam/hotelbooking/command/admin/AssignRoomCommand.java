package com.epam.hotelbooking.command.admin;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationService;
import com.epam.hotelbooking.service.BillService;
import com.epam.hotelbooking.service.RoomService;
import com.epam.hotelbooking.util.CostCalculator;
import com.epam.hotelbooking.util.DaysCalculator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;

public class AssignRoomCommand implements Command {

    private static final String PRICE_PARAM = "price";
    private static final String NOT_SELECTED = "&chooseError=roomChoose.notSelected&application_id=";
    private static final String IS_ASSIGNED = "&chooseError=roomChoose.isAssigned&application_id=";
    private static final String MANAGEMENT_ERROR_IS_ASSIGNED = "&managementError=roomChoose.isAssigned";
    private static final String MANAGEMENT_ERROR_CANCELED_APPLICATION = "&managementError=management.canceledApplication";

    private final BillService billService;
    private final RoomService roomService;
    private final ApplicationService applicationService;

    public AssignRoomCommand(BillService billService, RoomService roomService, ApplicationService applicationService) {
        this.billService = billService;
        this.roomService = roomService;
        this.applicationService = applicationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String roomIdParam = request.getParameter(Bill.ROOM_ID);
        String applicationIdParam = request.getParameter(Bill.APPLICATION_ID);

        if (roomIdParam == null || roomIdParam.isEmpty()) {
            return RedirectConstants.ROOM_CHOOSE_REDIRECT + NOT_SELECTED + applicationIdParam;
        }

        Long roomId = Long.parseLong(roomIdParam);
        Long applicationId = Long.parseLong(applicationIdParam);

        if (roomService.checkAssignment(roomId)) {
            return RedirectConstants.ROOM_CHOOSE_REDIRECT + IS_ASSIGNED + applicationIdParam;
        }

        if (billService.isApproved(applicationId)) {
            return RedirectConstants.MANAGEMENT_REDIRECT + MANAGEMENT_ERROR_IS_ASSIGNED;
        }

        Optional<Application> optionalApplication = applicationService.findApplicationById(applicationId);

        if (!optionalApplication.isPresent()) {
            throw new ServiceException(Application.TABLE_NAME + " with id " + applicationId + CommonConstants.NOT_EXIST);
        }

        Application application = optionalApplication.get();

        if (application.getApplicationStatus() == ApplicationStatus.CANCELED) {
            return RedirectConstants.MANAGEMENT_REDIRECT + MANAGEMENT_ERROR_CANCELED_APPLICATION;
        }

        DaysCalculator daysCalculator = new DaysCalculator();
        int days = daysCalculator.getDaysBetweenDates(application);

        BigDecimal price = new BigDecimal(request.getParameter(PRICE_PARAM));
        Integer places = application.getPlaces();

        CostCalculator costCalculator = new CostCalculator();
        BigDecimal cost = costCalculator.calculateCost(price, days, places);

        Long clientId = application.getClientId();

        billService.makeABill(new Bill(null, cost, roomId, applicationId, clientId));
        roomService.markRoom(roomId, RoomStatus.RESERVED);
        applicationService.markApplication(application, ApplicationStatus.APPROVED);

        return RedirectConstants.MANAGEMENT_REDIRECT;
    }


}
