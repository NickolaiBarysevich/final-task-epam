package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Identifiable;
import com.epam.hotelbooking.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    Optional<T> findById(Long id) throws DaoException;

    List<T> findAll() throws DaoException;

    boolean save(T item) throws DaoException;

    boolean removeById(Long id) throws DaoException;
}
