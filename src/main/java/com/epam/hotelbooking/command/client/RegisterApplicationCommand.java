package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class RegisterApplicationCommand implements Command {

    private static final String DATE_ERROR = "&someError=home.dateError";
    private static final String ROOM_CLASS_ERROR = "&someError=home.roomClassError";
    private static final String PLACES_ERROR = "&someError=home.placesError";
    private static final String REGISTER_THANKS_MESSAGE = "&message=profile.registerThanks";

    private final ApplicationService service;

    public RegisterApplicationCommand(ApplicationService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String guestsParam = request.getParameter(Application.PLACES);
        Integer quests = Integer.parseInt(guestsParam);

        if (quests.compareTo(0) < 0 || quests.compareTo(4) > 0) {
            return RedirectConstants.HOME_REDIRECT + PLACES_ERROR;
        }

        String checkIn = request.getParameter(Application.CHECK_IN_DATE);
        Date checkInDate = Date.valueOf(checkIn);

        String checkOut = request.getParameter(Application.CHECK_OUT_DATE);
        Date checkOutDate = Date.valueOf(checkOut);

        if (checkInDate.compareTo(checkOutDate) > -1) {
            return RedirectConstants.HOME_REDIRECT + DATE_ERROR;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        Long userId = user.getId();

        String roomClassIdValue = request.getParameter(Application.ROOM_CLASS_ID);
        Long roomClassId = Long.parseLong(roomClassIdValue);

        if (roomClassId.compareTo(1L) < 0 || roomClassId.compareTo(3L) > 0) {
            return RedirectConstants.HOME_REDIRECT + ROOM_CLASS_ERROR;
        }

        Application application = new Application(null, quests, checkInDate, checkOutDate,
                ApplicationStatus.CONSIDERING, userId, roomClassId);

        if (service.saveApplication(application)) {
            return RedirectConstants.APPLICATION_HISTORY_REDIRECT + REGISTER_THANKS_MESSAGE;
        }

        throw new ServiceException("application wasn't saved");

    }
}
