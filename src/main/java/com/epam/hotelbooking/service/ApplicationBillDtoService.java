package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.ApplicationBillDtoDao;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ApplicationBillDtoService {

    private final ApplicationBillDtoDao applicationBillDtoDao;

    public ApplicationBillDtoService(ApplicationBillDtoDao applicationBillDtoDao) {
        this.applicationBillDtoDao = applicationBillDtoDao;
    }

    public List<ApplicationBillDto> findApplicationDtoListByUserId(Long userId) throws ServiceException {
        try {
            return applicationBillDtoDao.findApplicationAndBillsByClientId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean checkApplicationBelongsToUser(ApplicationBillDto application, User user) throws ServiceException {
        Long userId = user.getId();
        return findApplicationDtoListByUserId(userId).contains(application);
    }


    public Optional<ApplicationBillDto> findApplicationDto(Long id) throws ServiceException {
        try {
            return applicationBillDtoDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
