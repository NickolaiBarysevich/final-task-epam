package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.UserRole;
import com.epam.hotelbooking.exception.BuilderException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates an object of the {@link User}.
 */
public class UserBuilder implements Builder<User> {

    /**
     * {@inheritDoc}
     *
     * @param parameters the result set from which an object must be created.
     * @return
     * @throws BuilderException
     */
    @Override
    public User build(ResultSet parameters) throws BuilderException {
        try {
            Long id = parameters.getLong(User.ID);
            String login = parameters.getString(User.USERNAME);
            String password = parameters.getString(User.PASSWORD);
            String name = parameters.getString(User.NAME);
            String lastName = parameters.getString(User.LAST_NAME);
            String phoneNumber = parameters.getString(User.PHONE_NUMBER);
            String email = parameters.getString(User.EMAIL);
            BigDecimal wallet = parameters.getBigDecimal(User.WALLET);
            String roleValue = parameters.getString(User.ROLE);
            UserRole role = UserRole.valueOf(roleValue.toUpperCase());

            return new User(id, login, password, name, lastName, phoneNumber, email, wallet, role);
        } catch (SQLException e) {
            throw new BuilderException(e.getMessage(), e.getCause());
        }
    }


}
