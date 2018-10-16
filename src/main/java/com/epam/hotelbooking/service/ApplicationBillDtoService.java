package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationBillDtoDao;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Class that helps command connect to database.
 * Works with {@link ApplicationBillDto} view.
 * May contain methods that have specific actions
 * on data from database.
 *
 * @author Nickolai Barysevich.
 */
public class ApplicationBillDtoService {

    private final ApplicationBillDtoDao applicationBillDtoDao;

    public ApplicationBillDtoService(ApplicationBillDtoDao applicationBillDtoDao) {
        this.applicationBillDtoDao = applicationBillDtoDao;
    }

    /**
     * Asks {@code applicationBillDtoDao} for find
     * records into {@link ApplicationBillDto} view
     * by client id.
     *
     * @param userId user id by which records will bew searched.
     * @return list of the {@link ApplicationBillDto}
     * @throws ServiceException if some dao error has occurred.
     */
    public List<ApplicationBillDto> findApplicationDtoListByUserId(Long userId) throws ServiceException {
        try {
            return applicationBillDtoDao.findApplicationAndBillsByClientId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Define if the application belong to user.
     *
     * @param application analysed application
     * @param user        user to whom application should belong
     * @return true if application belong to user
     * @throws ServiceException if some dao error has occurred.
     */
    public boolean checkApplicationBelongsToUser(ApplicationBillDto application, User user) throws ServiceException {
        Long userId = user.getId();
        return findApplicationDtoListByUserId(userId).contains(application);
    }

    /**
     * Finds {@link ApplicationBillDto} by application id.
     *
     * @param applicationId application id by which record will be searched.
     * @return optional of {@link ApplicationBillDto}.
     * @throws ServiceException if some dao error has occurred.
     */
    public Optional<ApplicationBillDto> findApplicationBillDto(Long applicationId) throws ServiceException {
        try {
            return applicationBillDtoDao.findById(applicationId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
