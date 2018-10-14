package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.RoomDao;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class RoomServiceTest {

    private static final long TEST_ROOM_ID = 1L;
    private static final Room TEST_ROOM_RESERVED = new Room(1L, null, RoomStatus.RESERVED, null);
    private static final Room TEST_ROOM_FREE = new Room(2L, null, RoomStatus.FREE, null);

    @Test
    public void shouldReturnTrueOnCheckAssigned() throws DaoException, ServiceException {
        RoomDao roomDao = mock(RoomDao.class);

        Room reservedRoom = new Room(null, null, RoomStatus.RESERVED, null);
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(reservedRoom));

        RoomService roomService = new RoomService(roomDao);
        boolean actual = roomService.checkAssignment(TEST_ROOM_ID);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnCheckAssigned() throws DaoException, ServiceException {
        RoomDao roomDao = mock(RoomDao.class);

        when(roomDao.findById(anyLong())).thenReturn(Optional.of(TEST_ROOM_FREE));

        RoomService roomService = new RoomService(roomDao);
        boolean actual = roomService.checkAssignment(TEST_ROOM_ID);

        Assert.assertFalse(actual);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void shouldThrowServiceExceptionOnCheckAssigned() throws DaoException, ServiceException {
        RoomDao roomDao = mock(RoomDao.class);

        when(roomDao.findById(anyLong())).thenReturn(Optional.empty());

        RoomService roomService = new RoomService(roomDao);
        roomService.checkAssignment(TEST_ROOM_ID);

    }

    @Test
    public void shouldReturnTrueOnMarkRoom() throws DaoException, ServiceException {
        RoomDao roomDao = mock(RoomDao.class);

        when(roomDao.findById(anyLong())).thenReturn(Optional.of(TEST_ROOM_RESERVED));
        when(roomDao.save(any(Room.class))).thenReturn(true);

        RoomService roomService = new RoomService(roomDao);
        boolean actual = roomService.markRoom(TEST_ROOM_ID, RoomStatus.FREE);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseOnMarkRoom() throws DaoException, ServiceException {
        RoomDao roomDao = mock(RoomDao.class);

        when(roomDao.findById(anyLong())).thenReturn(Optional.of(TEST_ROOM_RESERVED));
        when(roomDao.save(any(Room.class))).thenReturn(false);

        RoomService roomService = new RoomService(roomDao);
        boolean actual = roomService.markRoom(TEST_ROOM_ID, RoomStatus.FREE);

        Assert.assertFalse(actual);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void shouldThrowServiceExceptionOnMarkRoom() throws DaoException, ServiceException {
        RoomDao roomDao = mock(RoomDao.class);

        when(roomDao.findById(anyLong())).thenReturn(Optional.empty());

        RoomService roomService = new RoomService(roomDao);
        roomService.markRoom(TEST_ROOM_ID, RoomStatus.FREE);
    }

}