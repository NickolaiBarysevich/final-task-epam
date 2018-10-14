package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.UserDao;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Optional;

public class UserService {

    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public Optional<User> logIn(String login, String password) throws ServiceException {
        try {
            return dao.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean checkUsernameOnDuplicate(String login) throws ServiceException {
        try {
            Optional<User> optionalUser = dao.findUserByLogin(login);
            return optionalUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean checkEmailOnDuplicate(String email) throws ServiceException {
        try {
            Optional<User> optionalUser = dao.findUserByEmail(email);
            return optionalUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean saveUser(User user) throws ServiceException {
        try {
            return dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean checkPhoneNumberOnDuplicate(String phoneNumber) throws ServiceException {
        try {
            Optional<User> optionalUser = dao.findUserByPhoneNumber(phoneNumber);
            return optionalUser.isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


}
