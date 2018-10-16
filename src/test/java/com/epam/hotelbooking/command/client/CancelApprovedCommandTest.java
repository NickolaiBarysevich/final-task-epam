package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationService;
import com.epam.hotelbooking.service.BillService;
import com.epam.hotelbooking.service.RoomService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CancelApprovedCommandTest {

    private static final String APPLICATION_HISTORY_REDIRECT = RedirectConstants.APPLICATION_HISTORY_REDIRECT;
    private static final String TEST_APPLICATION_ID = "1";
    private static final Bill EMPTY_BILL = new Bill(1L, new BigDecimal("10"), 1L, 1L, 1L);
    private static final String MESSAGE_CANT_BE_CANCELED = "message=profile.cantBeCanceled";

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final BillService billService = mock(BillService.class);
    private final RoomService roomService = mock(RoomService.class);
    private final ApplicationService applicationService = mock(ApplicationService.class);

    @Test
    public void shouldReturnApplicationHistoryRedirect() throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(TEST_APPLICATION_ID);

        when(billService.findBillByApplicationId(anyLong())).thenReturn(Optional.of(EMPTY_BILL));
        when(billService.deleteBillByApplicationId(anyLong())).thenReturn(true);

        when(roomService.markRoom(anyLong(), any(RoomStatus.class))).thenReturn(true);

        when(applicationService.isCancelable(anyLong())).thenReturn(true);
        when(applicationService.markApplication(anyLong(), any(ApplicationStatus.class))).thenReturn(true);

        CancelApprovedCommand command = new CancelApprovedCommand(billService, roomService, applicationService);
        String actual = command.execute(request, response);

        Assert.assertEquals(actual, APPLICATION_HISTORY_REDIRECT);
    }

    @Test
    public void shouldReturnApplicationHistoryRedirectWithErrorMessage() throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(TEST_APPLICATION_ID);

        when(billService.findBillByApplicationId(anyLong())).thenReturn(Optional.of(EMPTY_BILL));
        when(billService.deleteBillByApplicationId(anyLong())).thenReturn(true);

        when(roomService.markRoom(anyLong(), any(RoomStatus.class))).thenReturn(true);

        when(applicationService.isCancelable(anyLong())).thenReturn(false);
        when(applicationService.markApplication(anyLong(), any(ApplicationStatus.class))).thenReturn(true);

        CancelApprovedCommand command = new CancelApprovedCommand(billService, roomService, applicationService);
        String actual = command.execute(request, response);

        Assert.assertEquals(actual, APPLICATION_HISTORY_REDIRECT + MESSAGE_CANT_BE_CANCELED);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void shouldThrowServiceException() throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(TEST_APPLICATION_ID);

        when(billService.findBillByApplicationId(anyLong())).thenReturn(Optional.empty());
        when(billService.deleteBillByApplicationId(anyLong())).thenReturn(true);

        when(roomService.markRoom(anyLong(), any(RoomStatus.class))).thenReturn(true);

        when(applicationService.isCancelable(anyLong())).thenReturn(true);
        when(applicationService.markApplication(anyLong(), any(ApplicationStatus.class))).thenReturn(true);

        CancelApprovedCommand command = new CancelApprovedCommand(billService, roomService, applicationService);
        command.execute(request, response);
    }

}