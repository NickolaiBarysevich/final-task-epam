package com.epam.hotelbooking.command.admin;

import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.Application;
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
import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AssignRoomCommandTest {

    private static final String TEST_ID_PARAM = "1";
    private static final String TEST_PRICE_PARAM = "20";
    private static final String MANAGEMENT_REDIRECT = RedirectConstants.MANAGEMENT_REDIRECT;
    private static final String MANAGEMENT_ERROR_NOT_ASSIGNABLE_APPLICATION = "&managementError=management.notAssignable";
    private static final Application TEST_APPROVED_APPLICATION =
            new Application(0L, 0, Date.valueOf("2018-10-10"), Date.valueOf("2018-10-10"), ApplicationStatus.APPROVED, 0L, 0L);
    private static final Application TEST_EXPIRED_APPLICATION =
            new Application(0L, 0, Date.valueOf("2018-10-10"), Date.valueOf("2018-10-10"), ApplicationStatus.EXPIRED, 0L, 0L);

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final BillService billService = mock(BillService.class);
    private final RoomService roomService = mock(RoomService.class);
    private final ApplicationService applicationService = mock(ApplicationService.class);

    @Test
    public void shouldReturnManagementRedirect() throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(TEST_ID_PARAM, TEST_ID_PARAM, TEST_PRICE_PARAM);

        when(billService.makeABill(any(Bill.class))).thenReturn(true);

        when(roomService.checkAssignment(anyLong())).thenReturn(false);
        when(roomService.markRoom(anyLong(), any(RoomStatus.class))).thenReturn(true);

        when(applicationService.markApplication(anyLong(), any(ApplicationStatus.class))).thenReturn(true);
        when(applicationService.findApplicationById(anyLong())).thenReturn(Optional.of(TEST_APPROVED_APPLICATION));

        AssignRoomCommand command = new AssignRoomCommand(billService, roomService, applicationService);
        String actual = command.execute(request, response);

        Assert.assertEquals(actual, MANAGEMENT_REDIRECT);
    }

    @Test
    public void shouldReturnManagementRedirectWithErrorMessage() throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(TEST_ID_PARAM, TEST_ID_PARAM, TEST_PRICE_PARAM);

        when(billService.makeABill(any(Bill.class))).thenReturn(true);

        when(roomService.checkAssignment(anyLong())).thenReturn(false);
        when(roomService.markRoom(anyLong(), any(RoomStatus.class))).thenReturn(true);

        when(applicationService.markApplication(anyLong(), any(ApplicationStatus.class))).thenReturn(true);
        when(applicationService.findApplicationById(anyLong())).thenReturn(Optional.of(TEST_EXPIRED_APPLICATION));

        AssignRoomCommand command = new AssignRoomCommand(billService, roomService, applicationService);
        String actual = command.execute(request, response);

        Assert.assertEquals(actual, MANAGEMENT_REDIRECT + MANAGEMENT_ERROR_NOT_ASSIGNABLE_APPLICATION);
    }

}