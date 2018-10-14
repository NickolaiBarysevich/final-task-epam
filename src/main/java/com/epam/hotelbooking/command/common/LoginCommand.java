package com.epam.hotelbooking.command.common;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.RedirectConstants;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.UserRole;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserService;
import com.epam.hotelbooking.util.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String INCORRECT_USERNAME_PARAM = "&loginError=login.incorrectUsername";
    private static final String WRONG_PASSWORD_PARAM = "&loginError=login.wrongPassword";

    private final UserService service;

    private final LoginValidator validator = new LoginValidator();

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String username = request.getParameter(User.USERNAME);
        if (!validator.validateUsername(username)) {
            return RedirectConstants.SHOW_LOGIN_REDIRECT + INCORRECT_USERNAME_PARAM;
        }

        String password = request.getParameter(User.PASSWORD);
        if (!validator.validatePassword(password)) {
            return RedirectConstants.SHOW_LOGIN_REDIRECT + WRONG_PASSWORD_PARAM;

        }

        Optional<User> optionalUser = service.logIn(username, password);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            HttpSession session = request.getSession(true);

            session.setAttribute(User.TABLE_NAME, user);
            UserRole userRole = user.getRole();
            session.setAttribute(User.ROLE, userRole);

            if (userRole == UserRole.ADMINISTRATOR) {
                return RedirectConstants.MANAGEMENT_REDIRECT;
            }
            return RedirectConstants.HOME_REDIRECT;
        }

        return RedirectConstants.SHOW_LOGIN_REDIRECT + WRONG_PASSWORD_PARAM;

    }
}
