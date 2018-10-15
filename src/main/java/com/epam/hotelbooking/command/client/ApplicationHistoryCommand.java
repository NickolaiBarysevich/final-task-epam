package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommonConstants;
import com.epam.hotelbooking.command.JspConstants;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.ApplicationBillDtoService;
import com.epam.hotelbooking.util.PaginationHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Paginates and represents user's application.
 *
 * @author Nickolai Barysevich.
 */
public class ApplicationHistoryCommand implements Command {

    private static final int RECORDS_AMOUNT = 5;
    private static final String APPLICATION_BILL_ATTRIBUTE = "applicationBill";

    private final ApplicationBillDtoService service;

    public ApplicationHistoryCommand(ApplicationBillDtoService service) {
        this.service = service;
    }

    /**
     * Sets as attribute paginated list of {@link ApplicationBillDto}
     * to the {@code request}.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return applicationHistory.jsp
     * @throws ServiceException if some service error has occurred.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        Long userId = user.getId();

        List<ApplicationBillDto> itemList = service.findApplicationDtoListByUserId(userId);

        PaginationHelper<ApplicationBillDto> paginationHelper = new PaginationHelper<>(itemList, RECORDS_AMOUNT);

        int page = paginationHelper.findPage(request);

        request.setAttribute(APPLICATION_BILL_ATTRIBUTE, paginationHelper.getPage(page));
        request.setAttribute(CommonConstants.NUMBER_OF_PAGES_ATTRIBUTE, paginationHelper.getNumberOfPages());
        request.setAttribute(CommonConstants.CURRENT_PAGE_ATTRIBUTE, page);

        return JspConstants.APPLICATION_HISTORY_JSP;

    }
}
