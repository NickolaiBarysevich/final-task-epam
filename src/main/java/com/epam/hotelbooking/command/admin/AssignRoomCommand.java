package com.epam.hotelbooking.command.admin;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.*;
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

/**
 * This is administrator command by which he allowed to
 * assign rooms to the {@link Application}.
 *
 * @author Nickolai Barysevich.
 */
public class AssignRoomCommand implements Command {

    /**
     * The name of the room price parameter
     */
    private static final String PRICE_PARAM = "price";

    /**
     * Error message for situation when user pressed the assign button
     * but didn't choose a room
     */
    private static final String NOT_SELECTED = "&chooseError=roomChoose.notSelected&application_id=";

    /**
     * Error message for situation when user trying to assign room which
     * is already assigned
     */
    private static final String IS_ASSIGNED = "&chooseError=roomChoose.isAssigned&application_id=";

    /**
     * Error message for situation when user trying to assign room
     * to not considering application
     */
    private static final String MANAGEMENT_ERROR_NOT_ASSIGNABLE_APPLICATION = "&managementError=management.notAssignable";

    /**
     * Gives methods to work with {@link Bill}
     */
    private final BillService billService;

    /**
     * Gives methods to work with {@link Room}
     */
    private final RoomService roomService;

    /**
     * Gives methods to work with {@link Application}
     */
    private final ApplicationService applicationService;


    public AssignRoomCommand(BillService billService, RoomService roomService, ApplicationService applicationService) {
        this.billService = billService;
        this.roomService = roomService;
        this.applicationService = applicationService;
    }

    /**
     * Getting information about {@link Room} and {@link Application}
     * from {@code request}. From this information creates a {@link Bill}
     * and set room status as "reserved"
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return management redirect or room choose redirect.
     * @throws ServiceException if some service error has occurred
     *                          or {@code optionalApplication} is {@code Optional.empty}.
     */
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


        Optional<Application> optionalApplication = applicationService.findApplicationById(applicationId);

        if (!optionalApplication.isPresent()) {
            throw new ServiceException(Application.TABLE_NAME + " with id " + applicationId + CommonConstants.NOT_EXIST);
        }

        Application application = optionalApplication.get();

        if (application.getApplicationStatus() != ApplicationStatus.CONSIDERING) {
            return RedirectConstants.MANAGEMENT_REDIRECT + MANAGEMENT_ERROR_NOT_ASSIGNABLE_APPLICATION;
        }

        DaysCalculator daysCalculator = new DaysCalculator();
        int days = daysCalculator.calculateDaysBetweenDates(application);

        BigDecimal price = new BigDecimal(request.getParameter(PRICE_PARAM));
        Integer places = application.getPlaces();

        CostCalculator costCalculator = new CostCalculator();
        BigDecimal cost = costCalculator.calculateCost(price, days, places);

        Long clientId = application.getClientId();

        billService.makeABill(new Bill(null, cost, roomId, applicationId, clientId));
        roomService.markRoom(roomId, RoomStatus.RESERVED);
        applicationService.markApplication(application.getId(), ApplicationStatus.APPROVED);

        return RedirectConstants.MANAGEMENT_REDIRECT;
    }


}
