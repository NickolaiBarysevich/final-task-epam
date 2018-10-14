package com.epam.hotelbooking.command.admin;

import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationDtoService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ManagementCommandTest {

    private static final List<ApplicationDto> EMPTY_APPLICATION_LIST = Collections.emptyList();
    private static final String MANAGEMENT_JSP = JspConstants.MANAGEMENT_JSP;

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final ApplicationDtoService applicationDtoService = mock(ApplicationDtoService.class);

    @Test
    public void shouldReturnManagementJsp() throws ServiceException {
        when(request.getParameter(anyString())).thenReturn(null);

        when(applicationDtoService.getApplications(null)).thenReturn(EMPTY_APPLICATION_LIST);

        ManagementCommand managementCommand = new ManagementCommand(applicationDtoService);
        String actual = managementCommand.execute(request, response);

        Assert.assertEquals(actual, MANAGEMENT_JSP);
    }

}