package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.UserDao;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Optional;

/**
 * Helps to get data from database table "user".
 *
 * @author Nickolai Barysevich.
 */
public class UserService {

    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    /**
     * Finds and return user which corresponds
     * to gotten username and password.
     *
     * @param username username that record should correspond to
     * @param password password that record should correspond to
     * @return optional of {@link User}.
     * @throws ServiceException if some dao error has occurred.
     */
    public Optional<User> logIn(String username, String password) throws ServiceException {
        try {
            return dao.findByLoginAndPassword(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Finds records in the table that corresponds to username.
     * Return true if the record was found.
     *
     * @param username username that record should correspond to
     * @return true if the record was found.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean checkUsernameOnDuplicate(String username) throws ServiceException {
        try {
            Optional<User> optionalUser = dao.findUserByLogin(username);
            return optionalUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Finds records in the table that corresponds to email.
     * Return true if the record was found.
     *
     * @param email email that record should correspond to
     * @return true if the record was found.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean checkEmailOnDuplicate(String email) throws ServiceException {
        try {
            Optional<User> optionalUser = dao.findUserByEmail(email);
            return optionalUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Finds records in the table that corresponds to phoneNumber.
     * Return true if the record was found.
     *
     * @param phoneNumber phoneNumber that record should correspond to
     * @return true if the record was found.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean checkPhoneNumberOnDuplicate(String phoneNumber) throws ServiceException {
        try {
            Optional<User> optionalUser = dao.findUserByPhoneNumber(phoneNumber);
            return optionalUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Saves user to the database.
     *
     * @param user user to be saved
     * @return true if user was saved.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean saveUser(User user) throws ServiceException {
        try {
            return dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
