package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.RoomDao;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Optional;

/**
 * Contains some methods that manipulates with
 * data from db table "room".
 *
 * @author Nickolai Barysevich.
 */
public class RoomService {

    private final RoomDao dao;

    public RoomService(RoomDao dao) {
        this.dao = dao;
    }

    /**
     * Marks room as specified status.
     *
     * @param roomId room to be marked.
     * @param status status to be set.
     * @return true if room was saved
     * @throws ServiceException if {@code !optionalRoom.isPresent()}
     */
    public boolean markRoom(Long roomId, RoomStatus status) throws ServiceException {
        try {
            Optional<Room> optionalRoom = dao.findById(roomId);
            if (optionalRoom.isPresent()) {
                Room room = optionalRoom.get();
                room.setStatus(status);
                return dao.save(room);
            }
            throw new ServiceException("room not found");

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Defines whether the room is assigned
     * to an application.
     *
     * @param roomId room to be checked.
     * @return true if room is assigned.
     * @throws ServiceException if {@code !optionalRoom.isPresent()}
     */
    public boolean checkAssignment(Long roomId) throws ServiceException {
        try {
            Optional<Room> optionalRoom = dao.findById(roomId);
            if (optionalRoom.isPresent()) {
                Room room = optionalRoom.get();

                RoomStatus status = room.getStatus();
                return status == RoomStatus.RESERVED || status == RoomStatus.BUSY;
            }
            throw new ServiceException("room not found");

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
