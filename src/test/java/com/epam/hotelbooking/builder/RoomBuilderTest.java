package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.exception.BuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class RoomBuilderTest {

    private static final long TEST_ID = 1L;
    private static final int TEST_BEDS = 2;

    private static final Room EXPECTED_ROOM = new Room(TEST_ID, TEST_BEDS, RoomStatus.FREE, TEST_ID);

    @Test
    public void shouldReturnRoom() throws SQLException, BuilderException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(anyString())).thenReturn(TEST_ID, TEST_ID);
        when(resultSet.getInt(anyString())).thenReturn(TEST_BEDS);
        when(resultSet.getString(anyString())).thenReturn(RoomStatus.FREE.getValue());

        RoomBuilder builder = new RoomBuilder();
        Room actual = builder.build(resultSet);

        Assert.assertEquals(actual, EXPECTED_ROOM);
    }



}