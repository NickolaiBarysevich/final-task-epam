package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationBillDtoService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ApplicationHistoryCommandTest {

    private static final String APPLICATION_HISTORY_JSP = JspConstants.APPLICATION_HISTORY_JSP;
    private static final List<ApplicationBillDto> EMPTY_APPLICATION_BILL_DTO_LIST = Collections.emptyList();
    private static final User EMPTY_USER = new User(1L, null, null, null, null, null, null, new BigDecimal(0), null);

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);
    private final ApplicationBillDtoService service = mock(ApplicationBillDtoService.class);

    @Test
    public void shouldReturnApplicationHistoryJsp() throws ServiceException {
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute(anyString())).thenReturn(EMPTY_USER);

        when(service.findApplicationDtoListByUserId(anyLong())).thenReturn(EMPTY_APPLICATION_BILL_DTO_LIST);

        ApplicationHistoryCommand command = new ApplicationHistoryCommand(service);
        String actual = command.execute(request, response);
        Assert.assertEquals(actual, APPLICATION_HISTORY_JSP);
    }

}