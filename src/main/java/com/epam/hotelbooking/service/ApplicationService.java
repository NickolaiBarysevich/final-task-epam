package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationDao;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Optional;

/**
 * Provides methods to work with {@link Application}
 * and "application" table.
 *
 * @author Nickolai Barysevich
 */
public class ApplicationService {

    private final ApplicationDao dao;

    public ApplicationService(ApplicationDao dao) {
        this.dao = dao;
    }

    /**
     * Marks application by specified status.
     *
     * @param id     application id that should be marked.
     * @param status status that must be set to application
     * @return true if application successfully marked.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean markApplication(Long id, ApplicationStatus status) throws ServiceException {
        try {
            return dao.markApplication(id, status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Saves application into the database table "application".
     *
     * @param application application to be saved.
     * @return true if application was saved.
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean saveApplication(Application application) throws ServiceException {
        try {
            return dao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    /**
     * Asks {@code dao} to find application by id.
     *
     * @param id application id by which it must be found.
     * @return optional of {@link Application}.
     * @throws ServiceException if some dao error has occurred.
     */
    public Optional<Application> findApplicationById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Defines whether the application has "canceled" status.
     *
     * @param applicationId application to be analysed.
     * @return true if application is canceled
     * @throws ServiceException if {@code !optionalApplication.isPresented()}
     */
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
