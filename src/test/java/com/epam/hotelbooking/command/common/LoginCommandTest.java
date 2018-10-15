package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.UserRole;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class LoginCommandTest {

    private static final String HOME_REDIRECT = RedirectConstants.APPLICATION_REGISTRATION_REDIRECT;
    private static final String MANAGEMENT_REDIRECT = RedirectConstants.MANAGEMENT_REDIRECT;
    private static final String SHOW_LOGIN_REDIRECT = RedirectConstants.SHOW_LOGIN_REDIRECT;

    private static final String INCORRECT_USERNAME_PARAM = "&loginError=login.incorrectUsername";
    private static final String WRONG_PASSWORD_PARAM = "&loginError=login.wrongPassword";

    private static final String TEST_USERNAME = "username";
    private static final String INCORRECT_USERNAME = "";
    private static final String TEST_PASSWORD = "Password1";
    private static final String WRONG_PASSWORD = "password";

    private static final User CLIENT_USER = new User(1L, TEST_USERNAME, TEST_PASSWORD, null, null,
            null, null, null, UserRole.CLIENT);
    private static final User ADMINISTRATOR_USER = new User(1L, TEST_USERNAME, TEST_PASSWORD, null, null,
            null, null, null, UserRole.ADMINISTRATOR);
    private static final User INCORRECT_USERNAME_USER = new User(1L, INCORRECT_USERNAME, TEST_PASSWORD, null, null,
            null, null, null, UserRole.CLIENT);
    private static final User WRONG_PASSWORD_USER = new User(1L, TEST_USERNAME, WRONG_PASSWORD, null, null,
            null, null, null, UserRole.CLIENT);

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);
    private final UserService userService = mock(UserService.class);

    @DataProvider
    private Object[][] testParameters() {
        return new Object[][] {
                {CLIENT_USER, HOME_REDIRECT, TEST_USERNAME, TEST_PASSWORD},
                {ADMINISTRATOR_USER, MANAGEMENT_REDIRECT, TEST_USERNAME, TEST_PASSWORD},
                {INCORRECT_USERNAME_USER, SHOW_LOGIN_REDIRECT + INCORRECT_USERNAME_PARAM, INCORRECT_USERNAME, TEST_PASSWORD},
                {WRONG_PASSWORD_USER, SHOW_LOGIN_REDIRECT + WRONG_PASSWORD_PARAM, TEST_USERNAME, WRONG_PASSWORD}
        };
    }

    @Test(dataProvider = "testParameters")
    public void shouldReturnRedirectPath(User user, String expected, String username, String password) throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(username, password);
        when(request.getSession(anyBoolean())).thenReturn(session);

        when(userService.logIn(anyString(), anyString())).thenReturn(Optional.of(user));

        LoginCommand loginCommand = new LoginCommand(userService);
        String actual = loginCommand.execute(request, response);

        Assert.assertEquals(actual, expected);
    }

}