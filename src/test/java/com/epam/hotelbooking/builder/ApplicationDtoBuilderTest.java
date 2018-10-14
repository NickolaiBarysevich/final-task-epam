package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.BuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ApplicationDtoBuilderTest {

    private static final long TEST_ID = 1L;
    private static final String TEST_USERNAME = "username";
    private static final String TEST_NAME = "name";
    private static final String TEST_PHONE_NUMBER = "phone_number";
    private static final int TEST_PLACES = 2;
    private static final Date TEST_CHECK_IN_DATE = Date.valueOf("2018-09-18");
    private static final Date TEST_CHECK_OUT_DATE = Date.valueOf("2018-09-25");
    private static final String TEST_ROOM_CLASS = "comfort";
    private static final BigDecimal TEST_COST = new BigDecimal("10");

    private static final ApplicationDto EXPECTED_OBJECT = new ApplicationDto(TEST_ID, TEST_USERNAME, TEST_NAME,
            TEST_PHONE_NUMBER, TEST_PLACES, TEST_CHECK_IN_DATE, TEST_CHECK_OUT_DATE, TEST_ROOM_CLASS, TEST_ID,
                    TEST_COST, TEST_ID);

    @Test
    public void shouldReturnApplicationDto() throws SQLException, BuilderException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(anyString())).thenReturn(TEST_ID, TEST_ID, TEST_ID);
        when(resultSet.getString(anyString())).thenReturn(TEST_USERNAME, TEST_NAME, TEST_PHONE_NUMBER, TEST_ROOM_CLASS);
        when(resultSet.getInt(anyString())).thenReturn(TEST_PLACES);
        when(resultSet.getDate(anyString())).thenReturn(TEST_CHECK_IN_DATE, TEST_CHECK_OUT_DATE);
        when(resultSet.getBigDecimal(anyString())).thenReturn(TEST_COST);

        ApplicationDtoBuilder builder = new ApplicationDtoBuilder();
        ApplicationDto actual = builder.build(resultSet);

        Assert.assertEquals(actual, EXPECTED_OBJECT);
    }

}