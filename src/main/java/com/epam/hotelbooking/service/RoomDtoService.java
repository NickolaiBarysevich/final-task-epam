package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.RoomDtoDao;
import com.epam.hotelbooking.entity.dto.RoomDto;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.List;

/**
 * Service {@link RoomDtoDao} to work with view of
 * "room" and "room_class"
 *
 * @author Nickolai Barysevich
 */
public class RoomDtoService {

    private static final String FREE_SORT_PARAM = "free";
    private static final String ALL_SORT_PARAM = "all";
    private static final String BUSY_SORT_PARAM = "busy";

    private final RoomDtoDao dao;

    public RoomDtoService(RoomDtoDao dao) {
        this.dao = dao;
    }

    /**
     * Return the list of the {@link RoomDto}.
     * A record must have status as specified.
     *
     * @param status status which must have records.
     * @return ist of the {@link RoomDto}.
     * @throws ServiceException if some dao error has occurred.
     */
    public List<RoomDto> getRoomList(String status) throws ServiceException {
        try {
            if (status == null) {
                status = FREE_SORT_PARAM;
            }

            switch (status) {
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
