package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.exception.BuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ApplicationBuilderTest {

    private static final long TEST_ID = 1L;
    private static final int TEST_PLACES = 2;
    private static final Date TEST_CHECK_IN_DATE = Date.valueOf("2018-10-10");
    private static final Date TEST_CHECK_OUT_DATE = Date.valueOf("2018-10-17");

    private static final Application EXPECTED_APPLICATION = new Application(TEST_ID, TEST_PLACES, TEST_CHECK_IN_DATE,
            TEST_CHECK_OUT_DATE, ApplicationStatus.CONSIDERING, TEST_ID, TEST_ID);

    @Test
    public void shouldReturnApplication() throws SQLException, BuilderException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(anyString())).thenReturn(TEST_ID, TEST_ID, TEST_ID);
        when(resultSet.getInt(anyString())).thenReturn(TEST_PLACES);
        when(resultSet.getDate(anyString())).thenReturn(TEST_CHECK_IN_DATE, TEST_CHECK_OUT_DATE);
        when(resultSet.getString(anyString())).thenReturn(ApplicationStatus.CONSIDERING.getValue());

        ApplicationBuilder builder = new ApplicationBuilder();
        Application actual = builder.build(resultSet);

        Assert.assertEquals(actual, EXPECTED_APPLICATION);
    }

}