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
import java.math.BigDecimal;

/**
 * Allows a new user create an account.
 *
 * @author Nickolai Barysevich.
 */
public class RegistrationCommand implements Command {

    /**
     * Message for username duplicate in database
     */
    private static final String USERNAME_DUPLICATE_PARAM = "&registrationError=registration.usernameDuplicate";

    /**
     * Message for email duplicate in database
     */
    private static final String EMAIL_DUPLICATE_PARAM = "&registrationError=registration.emailDuplicate";

    /**
     * Message for email duplicate in database
     */
    private static final String PHONE_NUMBER_DUPLICATE_PARAM = "&registrationError=registration.phoneDuplicate";

    /**
     * Message for for situations when password doesn't matches
     * the pattern
     */
    private static final String INCORRECT_PASSWORD_PARAM = "registrationError=registration.passwordTitle";

    private final UserService service;

    private final LoginValidator validator = new LoginValidator();

    public RegistrationCommand(UserService service) {
        this.service = service;
    }

    /**
     * Registers a new user.
     * Checks username, email and phone number on
     * duplicate in database. If it's have been
     * found specifies a redirect path with message.
     *
     * @param request http request that was got from browser
     * @param response http response that should be sent to browser
     * @return specified redirect path.
     * @throws ServiceException if some service error has occurred.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String username = request.getParameter(User.USERNAME);
        if (service.checkUsernameOnDuplicate(username)) {
            return RedirectConstants.SHOW_REGISTRATION_REDIRECT + USERNAME_DUPLICATE_PARAM;
        }

        String email = request.getParameter(User.EMAIL);
        if (service.checkEmailOnDuplicate(email)) {
            return RedirectConstants.SHOW_REGISTRATION_REDIRECT + EMAIL_DUPLICATE_PARAM;
        }

        String phoneNumber = request.getParameter(User.PHONE_NUMBER);
        if (service.checkPhoneNumberOnDuplicate(phoneNumber)) {
            return RedirectConstants.SHOW_REGISTRATION_REDIRECT + PHONE_NUMBER_DUPLICATE_PARAM;
        }

        String password = request.getParameter(User.PASSWORD);
        if (!validator.validatePassword(password)) {
            return RedirectConstants.SHOW_REGISTRATION_REDIRECT + INCORRECT_PASSWORD_PARAM;
        }

        String name = request.getParameter(User.NAME);
        String lastName = request.getParameter(User.LAST_NAME);

        User user = new User(null, username, password, name, lastName, phoneNumber, email, new BigDecimal(0), UserRole.CLIENT);

        service.saveUser(user);

        return RedirectConstants.SHOW_LOGIN_REDIRECT;

    }

}
