package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationService;
import com.epam.hotelbooking.service.BillService;
import com.epam.hotelbooking.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Allows user to cancel approved applications.
 *
 * @author Nickolai Barysevich.
 */
public class CancelApprovedCommand implements Command {

    private static final String MESSAGE_CANT_BE_CANCELED = "message=profile.cantBeCanceled";

    private final BillService billService;
    private final RoomService roomService;
    private final ApplicationService applicationService;

    public CancelApprovedCommand(BillService billService, RoomService roomService, ApplicationService applicationService) {
        this.billService = billService;
        this.roomService = roomService;
        this.applicationService = applicationService;
    }

    /**
     * Cancels approved application.
     *
     * @param request http request that was got from browser
     * @param response http response that should be sent to browser
     * @return redirect path to applicationHistory.jsp
     * @throws ServiceException if some service error has occurred
     *                          or {@code optionBill} is {Optional.empty}.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String applicationIdParam = request.getParameter(Bill.APPLICATION_ID);
        Long applicationId = Long.parseLong(applicationIdParam);

        if (!applicationService.isCancelable(applicationId)) {
            return RedirectConstants.APPLICATION_HISTORY_REDIRECT + MESSAGE_CANT_BE_CANCELED;
        }

        Optional<Bill> optionalBill = billService.findBillByApplicationId(applicationId);

        if (!optionalBill.isPresent()) {
            throw new ServiceException(Bill.TABLE_NAME + " with application " + applicationId + CommonConstants.NOT_EXIST);
        }

        Bill bill = optionalBill.get();

        roomService.markRoom(bill.getRoomId(), RoomStatus.FREE);
        billService.deleteBillByApplicationId(applicationId);
        applicationService.markApplication(applicationId, ApplicationStatus.CANCELED);

        return RedirectConstants.APPLICATION_HISTORY_REDIRECT;
    }


}
