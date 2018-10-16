package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationDao;
import com.epam.hotelbooking.dao.BillDao;
import com.epam.hotelbooking.dao.RoomDao;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Finds expired application and marks it as
 * "expired". If application was "approved" also
 * marks room which was assigned to application
 * and marks it as "free".
 */
public class ApplicationRoomBillService {

    private final ApplicationDao applicationDao;
    private final BillDao billDao;
    private final RoomService roomService;

    public ApplicationRoomBillService(ApplicationDao applicationDao, RoomDao roomDao, BillDao billDao) {
        this.applicationDao = applicationDao;
        roomService = new RoomService(roomDao);
        this.billDao = billDao;
    }

    /**
     * Finds all expired application an mark it.
     * Application considered as expired when
     * check out date for paid has passed or
     * when check in date for other has passed except
     * canceled.
     *
     * @throws ServiceException if some dao error has occurred.
     */
    public void findExpiredApplications() throws ServiceException {
        try {
            List<Application> applicationList = applicationDao.findAll();

            Date currentDate = new Date();
            for (Application application : applicationList) {
                Date checkOutDate = application.getCheckOutDate();
                Date checkInDate = application.getCheckInDate();
                ApplicationStatus applicationStatus = application.getApplicationStatus();

                if (applicationStatus == ApplicationStatus.PAID) {
                    if (checkOutDate.compareTo(currentDate) < 0) {
                        markApplicationAsExpired(application);
                        markRoomAsFree(application);
                    }
                } else if (applicationStatus != ApplicationStatus.CANCELED
                        && checkInDate.compareTo(currentDate) <= 0) {
                    markApplicationAsExpired(application);
                    markRoomAsFree(application);
                }

            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Marks application as expired.
     *
     * @param application application to mark.
     * @throws DaoException if some dao error has occurred.
     */
    private void markApplicationAsExpired(Application application) throws DaoException {
        application.setApplicationStatus(ApplicationStatus.EXPIRED);
        applicationDao.save(application);
    }

    /**
     * Marks room as free.
     *
     * @param application application that contains info about room.
     * @throws DaoException if some dao error has occurred.
     * @throws ServiceException if some service error has occurred.
     */
    private void markRoomAsFree(Application application) throws DaoException, ServiceException {
        Optional<Bill> optionalBill = billDao.findBillByApplicationId(application.getId());
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();
            roomService.markRoom(bill.getRoomId(), RoomStatus.FREE);
        }
    }
}
