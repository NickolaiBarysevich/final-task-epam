package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.UserRole;
import com.epam.hotelbooking.exception.BuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserBuilderTest {

    private static final long TEST_ID = 1L;
    private static final String TEST_USERNAME = "username";
    private static final String TEST_PASSWORD = "pass";
    private static final String TEST_NAME = "name";
    private static final String TEST_LAST_NAME = "lastName";
    private static final String TEST_PHONE_NUMBER = "phone";
    private static final String TEST_EMAIL = "email";
    private static final BigDecimal TEST_WALLET = new BigDecimal(0);

    private static final User EXPECTED_USER = new User(TEST_ID, TEST_USERNAME, TEST_PASSWORD, TEST_NAME,
            TEST_LAST_NAME, TEST_PHONE_NUMBER, TEST_EMAIL, TEST_WALLET, UserRole.ADMINISTRATOR);

    @Test
    public void shouldReturnUser() throws SQLException, BuilderException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(anyString())).thenReturn(TEST_ID);
        when(resultSet.getString(anyString())).thenReturn(TEST_USERNAME, TEST_PASSWORD, TEST_NAME,
                TEST_LAST_NAME, TEST_PHONE_NUMBER, TEST_EMAIL, UserRole.ADMINISTRATOR.getValue());
        when(resultSet.getBigDecimal(anyString())).thenReturn(TEST_WALLET);

        UserBuilder builder = new UserBuilder();
        User actual = builder.build(resultSet);

        Assert.assertEquals(actual, EXPECTED_USER);
    }

}