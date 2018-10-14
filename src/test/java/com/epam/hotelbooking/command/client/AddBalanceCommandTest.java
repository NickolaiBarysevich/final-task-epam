package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class AddBalanceCommandTest {

    private static final String BALANCE_REDIRECT = RedirectConstants.BALANCE_REDIRECT;
    private static final String INVALID_BALANCE_PARAM = "&balanceError=profile.invalidBalance";
    private static final String TEST_ADDING_VALUE = "100";
    private static final String INCORRECT_ADDING_VALUE = "string";
    private static final User EMPTY_USER = new User(1L, null, null, null, null, null, null, new BigDecimal(0), null);

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);
    private final UserService userService = mock(UserService.class);

    @DataProvider
    private Object[][] testParameters() {
        return new Object[][]{
                {TEST_ADDING_VALUE, BALANCE_REDIRECT},
                {INCORRECT_ADDING_VALUE, BALANCE_REDIRECT + INVALID_BALANCE_PARAM}
        };
    }

    @Test(dataProvider = "testParameters")
    public void shouldReturnBalanceRedirect(String addingValue, String expected) throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(addingValue);
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute(anyString())).thenReturn(EMPTY_USER);

        when(userService.saveUser(any(User.class))).thenReturn(true);

        AddBalanceCommand addBalanceCommand = new AddBalanceCommand(userService);
        String actual = addBalanceCommand.execute(request, response);

        Assert.assertEquals(actual, expected);
    }

}