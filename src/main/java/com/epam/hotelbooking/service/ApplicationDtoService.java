package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationDao;
import com.epam.hotelbooking.dao.ApplicationDtoDao;
import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.List;
import java.util.Optional;

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

    private List<ApplicationDto> findApplications() throws ServiceException {
        try {
            return applicationDtoDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<ApplicationDto> findApplications(ApplicationStatus status) throws ServiceException {
        try {
            return applicationDtoDao.findAll(status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    public Optional<ApplicationDto> findApplicationById(Long id) throws ServiceException {
        try {
            return applicationDtoDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean isCanceled(Long id) throws ServiceException {
        try {
            return applicationDao.findCanceledApplicationById(id).isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<ApplicationDto> getApplications(String sortParam) throws ServiceException {
        if (sortParam == null) {
            return findApplications(ApplicationStatus.CONSIDERING);
        }

        switch (sortParam) {
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

}
