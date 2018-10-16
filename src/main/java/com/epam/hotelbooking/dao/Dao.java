package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Identifiable;
import com.epam.hotelbooking.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Provides methods for work with database tables.
 * Parses information from tables into objects
 * available for work with them in java environment.
 * Tables should be parsed in objects that implements
 * interface {@link Identifiable}.
 *
 * @param <T> type of object that represents concrete table.
 */
public interface Dao<T extends Identifiable> {

    /**
     * Return optional object of the {@link T} found
     * by its id.
     *
     * @param id id of the element which should be found.
     * @return optional object of the {@link T}
     * @throws DaoException if some dao errors has occurred.
     */
    Optional<T> findById(Long id) throws DaoException;

    /**
     * Finds all records in the table and return the
     * list of them.
     *
     * @return list of {@link T}.
     * @throws DaoException if some dao errors has occurred.
     */
    List<T> findAll() throws DaoException;

    /**
     * Saves an object of {@link T} into table.
     *
     * @param item object to be saved.
     * @return true if object was saved,
     *          false if object wasn't saved.
     * @throws DaoException if some dao errors has occurred.
     */
    boolean save(T item) throws DaoException;

}
