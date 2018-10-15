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

/**
 * This command invokes when administrator log in into
 * system.
 * Paginates the list of applications. The can be sorted
 * by their status.
 *
 * @author Nickolai Barysevich.
 */
public class ManagementCommand implements Command {

    /**
     * Amount of applications that must be viewed on the
     * page.
     */
    private static final int RECORDS_PER_PAGE = 15;

    /**
     * Gives methods to work with {@link ApplicationDto}
     */
    private ApplicationDtoService service;

    public ManagementCommand(ApplicationDtoService service) {
        this.service = service;
    }

    /**
     * Paginates the list of {@link ApplicationDto} for
     * th management jsp page.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return management jsp page.
     * @throws ServiceException if some service error has occurred.
     */
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
