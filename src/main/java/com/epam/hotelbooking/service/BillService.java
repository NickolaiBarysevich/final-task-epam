package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.BillDao;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Optional;

public class BillService {

    private final BillDao dao;

    public BillService(BillDao dao) {
        this.dao = dao;
    }


    public boolean makeABill(Bill bill) throws ServiceException {
        try {
            return dao.save(bill);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean isApproved(Long applicationId) throws ServiceException {
        try {
            Optional<Bill> bill = dao.findBillByApplicationId(applicationId);
            return bill.isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Bill> findBillByApplicationId(Long id) throws ServiceException {
        try {
            return dao.findBillByApplicationId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean deleteBillByApplicationId(Long applicationId) throws ServiceException {
        try {
            return dao.removeBillByApplicationId(applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
