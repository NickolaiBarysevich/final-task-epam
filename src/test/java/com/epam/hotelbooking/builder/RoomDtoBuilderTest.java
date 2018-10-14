package com.epam.hotelbooking.builder;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.entity.RoomStatus;
import com.epam.hotelbooking.entity.dto.RoomDto;
import com.epam.hotelbooking.exception.BuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class RoomDtoBuilderTest {

    private static final long TEST_ID = 1L;
    private static final int TEST_BEDS = 2;
    private static final BigDecimal TEST_PRICE = new BigDecimal("20");

    private static final RoomDto EXPECTED_ROOM = new RoomDto(TEST_ID, TEST_BEDS, RoomClass.COMFORT.getValue(), TEST_PRICE);

    @Test
    public void shouldReturnRoomDto() throws SQLException, BuilderException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(anyString())).thenReturn(TEST_ID);
        when(resultSet.getInt(anyString())).thenReturn(TEST_BEDS);
        when(resultSet.getString(anyString())).thenReturn(RoomClass.COMFORT.getValue());
        when(resultSet.getBigDecimal(anyString())).thenReturn(TEST_PRICE);

        RoomDtoBuilder builder = new RoomDtoBuilder();
        RoomDto actual = builder.build(resultSet);

        Assert.assertEquals(actual, EXPECTED_ROOM);
    }

}