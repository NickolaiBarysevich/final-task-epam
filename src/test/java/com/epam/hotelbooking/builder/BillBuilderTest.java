package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.exception.BuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class BillBuilderTest {

    private static final long TEST_ID = 1L;
    private static final BigDecimal TEST_COST = new BigDecimal("30.33");

    private static final Bill EXPECTED_BILL =
            new Bill(TEST_ID, TEST_COST, TEST_ID, TEST_ID, TEST_ID);

    @Test
    public void shouldReturnBill() throws SQLException, BuilderException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(anyString())).thenReturn(TEST_ID, TEST_ID, TEST_ID, TEST_ID);
        when(resultSet.getBigDecimal(anyString())).thenReturn(TEST_COST);

        BillBuilder billBuilder = new BillBuilder();
        Bill actual = billBuilder.build(resultSet);

        Assert.assertEquals(actual, EXPECTED_BILL);
    }

}