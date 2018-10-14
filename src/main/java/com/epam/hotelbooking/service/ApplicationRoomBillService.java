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

public class ApplicationRoomBillService {

    private final ApplicationDao applicationDao;
    private final BillDao billDao;
    private final RoomService roomService;

    public ApplicationRoomBillService(ApplicationDao applicationDao, RoomDao roomDao, BillDao billDao) {
        this.applicationDao = applicationDao;
        roomService = new RoomService(roomDao);
        this.billDao = billDao;
    }

    public void findExpiredApplications() throws ServiceException {
        try {
            List<Application> applicationList = applicationDao.findAll();

            Date currentDate = new Date();
            for (Application application : applicationList) {
                Date checkOutDate = application.getCheckOutDate();
                ApplicationStatus applicationStatus = application.getApplicationStatus();

                if (checkOutDate.compareTo(currentDate) <= 0
                        && applicationStatus != ApplicationStatus.CANCELED) {
                    markApplicationAsExpired(application);
                    markRoomAsFree(application);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void markApplicationAsExpired(Application application) throws DaoException {
        application.setApplicationStatus(ApplicationStatus.EXPIRED);
        applicationDao.save(application);
    }

    private void markRoomAsFree(Application application) throws DaoException, ServiceException {
        Optional<Bill> optionalBill = billDao.findBillByApplicationId(application.getId());
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();
            roomService.markRoom(bill.getRoomId(), RoomStatus.FREE);
        }
    }
}
