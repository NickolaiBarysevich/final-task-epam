package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationDao;
import com.epam.hotelbooking.dao.ApplicationDtoDao;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Class that helps command connect to database.
 * Works with {@link ApplicationDto} view.
 * May contain methods that have specific actions
 * on data from database.
 *
 * @author Nickolai Barysevich.
 */
public class ApplicationDtoService {

    private static final String PAID_SORT = "paid";
    private static final String ALL_SORT = "all";
    private static final String APPROVED_SORT = "approved";
    private static final String CANCELED_SORT = "canceled";
    private static final String EXPIRED_SORT = "expired";

    private final ApplicationDtoDao applicationDtoDao;
    private final ApplicationDao applicationDao;

    public ApplicationDtoService(ApplicationDtoDao applicationDtoDao, ApplicationDao applicationDao) {
        this.applicationDtoDao = applicationDtoDao;
        this.applicationDao = applicationDao;
    }

    /**
     * Asks {@code  applicationDtoDao} to find
     * all records in the view and return the
     * list of them.
     *
     * @return list of {@link ApplicationDto}.
     * @throws ServiceException if some dao errors has occurred.
     */
    private List<ApplicationDto> findApplications() throws ServiceException {
        try {
            return applicationDtoDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Asks {@code  applicationDtoDao} to find
     * all records with specified status in the
     * view and return the list of them.
     *
     * @param status status by which records will be searched.
     * @return list of {@link ApplicationDto}.
     * @throws ServiceException if some dao errors has occurred.
     */
    private List<ApplicationDto> findApplications(ApplicationStatus status) throws ServiceException {
        try {
            return applicationDtoDao.findAll(status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Asks {@code  applicationDtoDao} to find
     * a record by application by id.
     *
     * @param applicationId id by which record will be searched.
     * @return list of {@link ApplicationDto}.
     * @throws ServiceException if some dao errors has occurred.
     */
    public Optional<ApplicationDto> findApplicationById(Long applicationId) throws ServiceException {
        try {
            return applicationDtoDao.findById(applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Return the list of {@link ApplicationDto}
     * with concrete status.
     *
     * @param status staus by which records will be searched.
     * @return the list of {@link ApplicationDto} with concrete status.
     * @throws ServiceException if some dao errors has occurred.
     */
    public List<ApplicationDto> getApplications(String status) throws ServiceException {
        if (status == null) {
            return findApplications(ApplicationStatus.CONSIDERING);
        }

        switch (status) {
            case ALL_SORT:
                return findApplications();
            case APPROVED_SORT:
                return findApplications(ApplicationStatus.APPROVED);
            case PAID_SORT:
                return findApplications(ApplicationStatus.PAID);
            case CANCELED_SORT:
                return findApplications(ApplicationStatus.CANCELED);
            case EXPIRED_SORT:
                return findApplications(ApplicationStatus.EXPIRED);
            default:
                return findApplications(ApplicationStatus.CONSIDERING);

        }
    }

    /**
     * Define whether the application is considering.
     *
     * @param applicationId id of the application that must be defined
     * @return true if application is considering
     * @throws ServiceException ui {@code !optionalApplication.isPresented()}
     */
    public boolean isConsidering(Long applicationId) throws ServiceException {
        try {
            Optional<Application> optionalApplication = applicationDao.findById(applicationId);
            if (optionalApplication.isPresent()) {
                Application application = optionalApplication.get();
                return application.getApplicationStatus() == ApplicationStatus.CONSIDERING;
            }

            throw new ServiceException("application doesn't exist");
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
