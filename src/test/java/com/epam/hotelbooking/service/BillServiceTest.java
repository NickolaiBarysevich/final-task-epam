package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.BillDao;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class BillServiceTest {

    private static final Bill TEST_BILL = new Bill(null, null, null, null, null);
    private static final long TEST_APPLICATION_ID = 1L;

    private final BillDao billDao = mock(BillDao.class);

    @Test
    public void shouldReturnTrueOnMakeBill() throws DaoException, ServiceException {
        when(billDao.save(any(Bill.class))).thenReturn(true);

        BillService service = new BillService(billDao);
        boolean actual = service.makeABill(TEST_BILL);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnMakeBill() throws DaoException, ServiceException {
        when(billDao.save(any(Bill.class))).thenReturn(false);

        BillService service = new BillService(billDao);
        boolean actual = service.makeABill(TEST_BILL);

        Assert.assertFalse(actual);
    }

    @Test
    public void shouldReturnTrueOnIsApproved() throws DaoException, ServiceException {
        when(billDao.findBillByApplicationId(anyLong())).thenReturn(Optional.of(TEST_BILL));

        BillService service = new BillService(billDao);
        boolean actual = service.isApproved(TEST_APPLICATION_ID);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnIsApproved() throws DaoException, ServiceException {
        when(billDao.findBillByApplicationId(anyLong())).thenReturn(Optional.empty());

        BillService service = new BillService(billDao);
        boolean actual = service.isApproved(TEST_APPLICATION_ID);

        Assert.assertFalse(actual);
    }

    @Test
    public void shouldReturnOptionOfBill() throws DaoException, ServiceException {
        when(billDao.findBillByApplicationId(anyLong())).thenReturn(Optional.of(TEST_BILL));

        BillService service = new BillService(billDao);
        Optional<Bill> actual = service.findBillByApplicationId(TEST_APPLICATION_ID);

        Assert.assertEquals(actual, Optional.of(TEST_BILL));
    }

    @Test
    public void shouldReturnTrueOnDeleteBillByApplicationId() throws DaoException, ServiceException {
        when(billDao.removeBillByApplicationId(anyLong())).thenReturn(true);

        BillService service = new BillService(billDao);
        boolean actual = service.deleteBillByApplicationId(TEST_APPLICATION_ID);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnDeleteBillByApplicationId() throws DaoException, ServiceException {
        when(billDao.removeBillByApplicationId(anyLong())).thenReturn(false);

        BillService service = new BillService(billDao);
        boolean actual = service.deleteBillByApplicationId(TEST_APPLICATION_ID);

        Assert.assertFalse(actual);
    }

}