package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.RoomDtoDao;
import com.epam.hotelbooking.entity.dto.RoomDto;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.List;

public class RoomDtoService {

    private static final String FREE_SORT_PARAM = "free";
    private static final String ALL_SORT_PARAM = "all";
    private static final String BUSY_SORT_PARAM = "busy";

    private final RoomDtoDao dao;

    public RoomDtoService(RoomDtoDao dao) {
        this.dao = dao;
    }


    public List<RoomDto> getRoomList(String sortParam) throws ServiceException {
        try {
            if (sortParam == null) {
                sortParam = FREE_SORT_PARAM;
            }

            switch (sortParam) {
                case ALL_SORT_PARAM:
                    return dao.findAll();
                case BUSY_SORT_PARAM:
                    return dao.findAll(false);
                default:
                    return dao.findAll(true);

            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }


}
