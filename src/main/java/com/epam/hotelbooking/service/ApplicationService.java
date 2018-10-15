package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationDao;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

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

    public boolean isCancelable(Long applicationId) throws ServiceException {
        try {

            Optional<Application> optionalApplication = dao.findById(applicationId);
            if (optionalApplication.isPresent()) {
                Application application = optionalApplication.get();
                ApplicationStatus status = application.getApplicationStatus();
                return status == ApplicationStatus.CONSIDERING
                        || status == ApplicationStatus.APPROVED;
            }

            throw new ServiceException("application doesn't exist");
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


}
