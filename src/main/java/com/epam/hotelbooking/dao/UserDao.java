package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final String SAVE_QUERY_VALUES =
            " VALUES(?, ?, sha2(?, 256), ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE wallet=?";

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM " + User.TABLE_NAME +
            " WHERE username=? AND password=sha2(?, 256)";

    private static final String FIND_BY_LOGIN = "SELECT * FROM " + User.TABLE_NAME + " WHERE username=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM " + User.TABLE_NAME + " WHERE email=?";
    private static final String FIND_BY_PHONE_NUMBER = "SELECT * FROM " + User.TABLE_NAME + " WHERE phone_number=?";

    public UserDao(Connection connection) {
        super(connection);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    public Optional<User> findUserByLogin(String login) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN, login);
    }

    public Optional<User> findUserByEmail(String email) throws DaoException {
        return executeForSingleResult(FIND_BY_EMAIL, email);

    }

    public Optional<User> findUserByPhoneNumber(String phoneNumber) throws DaoException {
        return executeForSingleResult(FIND_BY_PHONE_NUMBER, phoneNumber);
    }

    @Override
    protected String getTableName() {
        return User.TABLE_NAME;
    }

    @Override
    protected String getSaveQuery() {
        return SAVE_QUERY_VALUES;
    }

    @Override
    protected Object[] extractValuesForSaving(User item) {
        List<Object> values = new ArrayList<>();

        values.add(item.getId());
        values.add(item.getUsername());
        values.add(item.getPassword());
        values.add(item.getName());
        values.add(item.getLastName());
        values.add(item.getPhoneNumber());
        values.add(item.getEmail());
        values.add(item.getWallet());
        values.add(item.getRole());

        //for duplicate insertion
        values.add(item.getWallet());

        return values.toArray();
    }
}
