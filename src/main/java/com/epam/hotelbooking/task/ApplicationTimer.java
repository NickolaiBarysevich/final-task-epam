package com.epam.hotelbooking.task;

import com.epam.hotelbooking.dao.manager.DaoManager;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.service.ApplicationRoomBillService;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Timer;

/**
 * Timer for check applications whether them is expired.
 *
 * @author Nickolai Barysevich
 */
public class ApplicationTimer {

    private static final int TWELVE_AM = 12;
    private static final int TWENTY_FOUR_HOURS = 1000 * 60 * 60 * 24;

    private final Logger logger = Logger.getLogger(ApplicationTimer.class);

    /**
     * Start the timer
     */
    public void startTimer() {
        try (DaoManager daoManager = new DaoManager()) {

            Timer timer = new Timer();

            ApplicationRoomBillService service = new ApplicationRoomBillService(daoManager.getApplicationDao(),
                    daoManager.getRoomDao(), daoManager.getBillDao());

            Thread checker = new Thread(new ApplicationChecker(service));

            Calendar date = Calendar.getInstance();
            date.set(Calendar.HOUR_OF_DAY, TWELVE_AM);

            timer.schedule(new ApplicationCheckTask(checker), date.getTime(), TWENTY_FOUR_HOURS);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
