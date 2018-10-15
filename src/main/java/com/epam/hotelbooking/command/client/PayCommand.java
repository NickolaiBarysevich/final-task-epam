package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.*;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationService;
import com.epam.hotelbooking.service.BillService;
import com.epam.hotelbooking.service.RoomService;
import com.epam.hotelbooking.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Allows user to pay for the room.
 *
 * @author Nickolai Barysevich.
 */
public class PayCommand implements Command {

    /**
     * Error message if there is not enough money on
     * user's wallet
     */
    private static final String NOT_ENOUGH_MONEY_MESSAGE = "&message=profile.notEnoughMoney";

    /**
     * Thanks message for the payment
     */
    private static final String PAYMENT_THANKS_MESSAGE = "&message=profile.paymentThanks";

    private final ApplicationService applicationService;
    private final UserService userService;
    private final BillService billService;
    private final RoomService roomService;

    public PayCommand(ApplicationService applicationService, UserService userService, BillService billService, RoomService roomService) {
        this.applicationService = applicationService;
        this.userService = userService;
        this.billService = billService;
        this.roomService = roomService;
    }

    /**
     * Changes the status of the application of "paid" and
     * the room status on "busy". Subtract the room price
     * from user's wallet. After that the application is
     * considered as paid.
     *
     * @param request http request that was got from browser
     * @param response http response that should be sent to browser
     * @return the specified redirect path.
     * @throws ServiceException if some service error has occurred
     *                          or {@code optionApplication} is {Optional.empty}
     *                          or {@code optionBill} is {Optional.empty}.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String applicationIdParam = request.getParameter(Bill.APPLICATION_ID);
        if (applicationIdParam == null || applicationIdParam.isEmpty()) {
            return RedirectConstants.PROFILE_REDIRECT;
        }

        Long applicationId = Long.parseLong(applicationIdParam);
        Optional<Application> optionalApplication = applicationService.findApplicationById(applicationId);


        if (!optionalApplication.isPresent()) {
            throw new ServiceException(Application.TABLE_NAME + " with id " + applicationId + CommonConstants.NOT_EXIST);
        }

        Application application = optionalApplication.get();

        if (application.getApplicationStatus() != ApplicationStatus.APPROVED) {
            return RedirectConstants.APPLICATION_HISTORY_REDIRECT;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        BigDecimal userWallet = user.getWallet();

        Optional<Bill> optionalBill = billService.findBillByApplicationId(applicationId);
        if (!optionalBill.isPresent()) {
            throw new ServiceException(Bill.TABLE_NAME + " with application " + applicationId + CommonConstants.NOT_EXIST);
        }

        Bill bill = optionalBill.get();
        BigDecimal applicationCost = bill.getCost();

        if (userWallet.compareTo(applicationCost) < 0) {
            return RedirectConstants.APPLICATION_HISTORY_REDIRECT + NOT_ENOUGH_MONEY_MESSAGE;
        }

        userWallet = userWallet.subtract(applicationCost);
        user.setWallet(userWallet);
        userService.saveUser(user);

        application.setApplicationStatus(ApplicationStatus.PAID);
        applicationService.saveApplication(application);

        roomService.markRoom(bill.getRoomId(), RoomStatus.BUSY);

        return RedirectConstants.APPLICATION_HISTORY_REDIRECT + PAYMENT_THANKS_MESSAGE;

    }
}
