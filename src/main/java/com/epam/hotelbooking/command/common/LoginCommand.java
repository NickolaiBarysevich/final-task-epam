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

/**
 * Allows users to log in.
 * This command uses {@link UserService} to search
 * a record in db which matches username and
 * password. If there is a record creates a new
 * session for that user.
 *
 * @author Nickolai Barysevich
 */
public class LoginCommand implements Command {

    /**
     * Error message for situations when username
     * is incorrect
     */
    private static final String INCORRECT_USERNAME_PARAM = "&loginError=login.incorrectUsername";

    /**
     * Error message for situations when username
     * is wrong
     */
    private static final String WRONG_PASSWORD_PARAM = "&loginError=login.wrongPassword";

    private final UserService service;

    private final LoginValidator validator = new LoginValidator();

    public LoginCommand(UserService service) {
        this.service = service;
    }

    /**
     * Tries to find a record in database that matches
     * gotten username and password. If the user was
     * found creates a new session.
     *
     * @param request  http request that was got from browser
     * @param response http response that should be sent to browser
     * @return specified redirect path.
     * @throws ServiceException if some service error has occurred.
     */
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
            return RedirectConstants.APPLICATION_REGISTRATION_REDIRECT;
        }

        return RedirectConstants.SHOW_LOGIN_REDIRECT + WRONG_PASSWORD_PARAM;

    }
}
