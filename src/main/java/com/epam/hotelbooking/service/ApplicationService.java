package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationDao;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ApplicationService {

    private final ApplicationDao dao;

    public ApplicationService(ApplicationDao dao) {
        this.dao = dao;
    }


    public boolean markApplication(Long id, ApplicationStatus status) throws ServiceException {
        try {
            return dao.markApplication(id, status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean markApplication(Application application, ApplicationStatus status) throws ServiceException {
        try {
            return dao.markApplication(application.getId(), status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    public List<Application> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean saveApplication(Application application) throws ServiceException {
        try {
            return dao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Application> findApplicationById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
