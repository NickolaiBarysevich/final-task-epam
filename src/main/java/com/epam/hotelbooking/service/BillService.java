package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.BillDao;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Optional;

/**
 * Helps command to communicate with table "bill"
 * in database.
 *
 * @author Nickolai Barysevich
 */
public class BillService {

    private final BillDao dao;

    public BillService(BillDao dao) {
        this.dao = dao;
    }

    /**
     * Saves {@link Bill} into the database table "bill".
     *
     * @param bill bill to be saved.
     * @return true if bill was saved.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean makeABill(Bill bill) throws ServiceException {
        try {
            return dao.save(bill);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Asks {@code dao} to find {@link Bill} into the table
     * "bill" by application id.
     *
     * @param applicationId application id by which bill will be searched.
     * @return optional of {@link Bill}.
     * @throws ServiceException if some dao error has occurred.
     */
    public Optional<Bill> findBillByApplicationId(Long applicationId) throws ServiceException {
        try {
            return dao.findBillByApplicationId(applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Asks {@code dao} to delete record from the
     * table "bill" which has application id as gotten.
     *
     * @param applicationId application id by which record will be deleted.
     * @return true of bill was deleted.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean deleteBillByApplicationId(Long applicationId) throws ServiceException {
        try {
            return dao.removeBillByApplicationId(applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
