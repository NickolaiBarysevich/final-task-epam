package com.epam.hotelbooking.command.client;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserService;
import com.epam.hotelbooking.util.DecimalValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class AddBalanceCommand implements Command {

    private static final String ADDING_VALUE = "addingValue";
    private static final String INVALID_BALANCE_PARAM = "&balanceError=profile.invalidBalance";

    private final UserService service;
    private final DecimalValidator validator = new DecimalValidator();

    public AddBalanceCommand(UserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String addingValueParam = request.getParameter(ADDING_VALUE);

        if (!validator.validate(addingValueParam)) {
            return RedirectConstants.BALANCE_REDIRECT + INVALID_BALANCE_PARAM;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);

        BigDecimal wallet = user.getWallet();
        BigDecimal addingValue = new BigDecimal(addingValueParam);
        BigDecimal updatedWallet = wallet.add(addingValue);

        user.setWallet(updatedWallet);
        service.saveUser(user);

        return RedirectConstants.BALANCE_REDIRECT;

    }
}
