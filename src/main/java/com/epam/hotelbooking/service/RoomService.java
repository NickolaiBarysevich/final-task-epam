package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.RoomDao;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;

import java.util.Optional;

public class RoomService {

    private final RoomDao dao;

    public RoomService(RoomDao dao) {
        this.dao = dao;
    }


    public boolean markRoom(Long roomId, RoomStatus status) throws ServiceException {
        try {
            Optional<Room> optionalRoom = dao.findById(roomId);
            if (optionalRoom.isPresent()) {
                Room room = optionalRoom.get();
                room.setStatus(status);
                return dao.save(room);
            } else {
                throw new ServiceException("room not found");
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    public boolean checkAssignment(Long roomId) throws ServiceException {
        try {
            Optional<Room> optionalRoom = dao.findById(roomId);
            if (optionalRoom.isPresent()) {
                Room room = optionalRoom.get();

                RoomStatus status = room.getStatus();
                return status == RoomStatus.RESERVED || status == RoomStatus.BUSY;
            } else {
                throw new ServiceException("room not found");
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
