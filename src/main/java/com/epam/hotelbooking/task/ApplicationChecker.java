package com.epam.hotelbooking.task;

import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationRoomBillService;
import org.apache.log4j.Logger;


public class ApplicationChecker implements Runnable {

    private final Logger logger = Logger.getLogger(ApplicationChecker.class);

    private final ApplicationRoomBillService service;

    public ApplicationChecker(ApplicationRoomBillService service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            service.findExpiredApplications();
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
