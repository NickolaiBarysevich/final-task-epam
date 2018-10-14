package com.epam.hotelbooking.command.admin;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationDtoService;
import com.epam.hotelbooking.util.PaginationHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagementCommand implements Command {

    private static final int RECORDS_PER_PAGE = 15;

    private ApplicationDtoService service;

    public ManagementCommand(ApplicationDtoService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String sortParam = request.getParameter(CommonConstants.SORT_PARAM);
        List<ApplicationDto> itemList = service.getApplications(sortParam);

        PaginationHelper<ApplicationDto> paginationHelper = new PaginationHelper<>(itemList, RECORDS_PER_PAGE);

        int page = paginationHelper.findPage(request);
        request.setAttribute(CommonConstants.APPLICATIONS_ATTRIBUTE, paginationHelper.getPage(page));
        request.setAttribute(CommonConstants.NUMBER_OF_PAGES_ATTRIBUTE, paginationHelper.getNumberOfPages());
        request.setAttribute(CommonConstants.CURRENT_PAGE_ATTRIBUTE, page);
        request.setAttribute(CommonConstants.SORT_PARAM, sortParam);

        return JspConstants.MANAGEMENT_JSP;

    }

}
