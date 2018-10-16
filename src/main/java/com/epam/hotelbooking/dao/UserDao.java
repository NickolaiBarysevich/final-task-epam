package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Works with table "user" and its representation
 * {@link User}.
 *
 * @author Nickolai Barysevich
 */
public class UserDao extends AbstractDao<User> {

    /**
     * Second part for insertion query
     */
    private static final String SAVE_QUERY_VALUES =
            " VALUES(?, ?, sha2(?, 256), ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE wallet=?";

    /**
     * Query that found user by login and password.
     * Needed for {@link #findByLoginAndPassword(String, String)}
     */
    private static final String FIND_BY_LOGIN_AND_PASSWORD = SELECT_FROM_QUERY + User.TABLE_NAME +
            " WHERE username=? AND password=sha2(?, 256)";

    /**
     * Searches a record with specified username
     */
    private static final String FIND_BY_LOGIN = "SELECT * FROM " + User.TABLE_NAME + " WHERE username=?";

    /**
     * Searches a record with specified email
     */
    private static final String FIND_BY_EMAIL = "SELECT * FROM " + User.TABLE_NAME + " WHERE email=?";

    /**
     * Searches a record with specified phone number
     */
    private static final String FIND_BY_PHONE_NUMBER = "SELECT * FROM " + User.TABLE_NAME + " WHERE phone_number=?";

    public UserDao(Connection connection) {
        super(connection);
    }

    /**
     * Finds record in the table where username
     * and password corresponds to the gotten.
     *
     * @param username username by which record will be searched
     * @param password password by which record will be searched
     * @return optional of user.
     * @throws DaoException if some dao error has occurred.
     */
    public Optional<User> findByLoginAndPassword(String username, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, username, password);
    }

    /**
     * Finds record in the table where username
     * corresponds to the gotten one.
     *
     * @param username username by which record will be searched
     * @return optional of user.
     * @throws DaoException if some dao error has occurred.
     */
    public Optional<User> findUserByLogin(String username) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN, username);
    }

    /**
     * Finds record in the table where email
     * corresponds to the gotten one.
     *
     * @param email username by which record will be searched
     * @return optional of user.
     * @throws DaoException if some dao error has occurred.
     */
    public Optional<User> findUserByEmail(String email) throws DaoException {
        return executeForSingleResult(FIND_BY_EMAIL, email);

    }

    /**
     * Finds record in the table where phone number
     * corresponds to the gotten one.
     *
     * @param phoneNumber username by which record will be searched
     * @return optional of user.
     * @throws DaoException if some dao error has occurred.
     */
    public Optional<User> findUserByPhoneNumber(String phoneNumber) throws DaoException {
        return executeForSingleResult(FIND_BY_PHONE_NUMBER, phoneNumber);
    }

    /**
     * Returns table name.
     *
     * @return table name.
     */
    @Override
    protected String getTableName() {
        return User.TABLE_NAME;
    }

    /**
     * Returns the second part of the save query.
     *
     * @return the second part of the save query.
     */
    @Override
    protected String getSaveQuery() {
        return SAVE_QUERY_VALUES;
    }

    /**
     * Extracts item fields to the object
     * array.
     *
     * @param item item to be extracted.
     * @return object array.
     */
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
