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

public class RegistrationCommand implements Command {

    private static final String USERNAME_DUPLICATE_PARAM = "&registrationError=registration.loginDuplicate";
    private static final String EMAIL_DUPLICATE_PARAM = "&registrationError=registration.emailDuplicate";
    private static final String PHONE_NUMBER_DUPLICATE_PARAM = "&registrationError=registration.phoneDuplicate";
    private static final String INCORRECT_PASSWORD_PARAM = "registrationError=registration.passwordTitle";

    private final UserService service;

    private final LoginValidator validator = new LoginValidator();

    public RegistrationCommand(UserService service) {
        this.service = service;
    }

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
