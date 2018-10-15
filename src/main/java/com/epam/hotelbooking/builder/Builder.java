package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Identifiable;
import com.epam.hotelbooking.exception.BuilderException;

import java.sql.ResultSet;

/**
 * Creates an object of the needed type.
 * Contains one method {@link #build(ResultSet)} which realizes logic
 * of creation an object.
 *
 * @param <T> the type of element that must be created.
 *
 * @author Nickolai Barysevich.
 */
public interface Builder<T extends Identifiable> {

    /**
     * Creates an object of the class {@code <T>}.
     * As argument takes {@link ResultSet} from which an object must
     * be created.
     *
     * @param parameters the result set from which an object must be created.
     * @return a new object of the type {@code <T>}.
     * @throws BuilderException throws when some builder exception has occurred.
     */
    T build(ResultSet parameters) throws BuilderException;
}
