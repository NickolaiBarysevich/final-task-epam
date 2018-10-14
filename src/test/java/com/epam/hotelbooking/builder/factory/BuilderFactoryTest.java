package com.epam.hotelbooking.builder.factory;

import com.epam.hotelbooking.builder.*;
import com.epam.hotelbooking.entity.Application;
import com.epam.hotelbooking.entity.Bill;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.entity.dto.ApplicationBillDto;
import com.epam.hotelbooking.entity.dto.ApplicationDto;
import com.epam.hotelbooking.entity.dto.RoomDto;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BuilderFactoryTest {

    @DataProvider
    private Object[][] testParameters(){
        return new Object[][] {
                {User.TABLE_NAME, UserBuilder.class},
                {Bill.TABLE_NAME, BillBuilder.class},
                {Application.TABLE_NAME, ApplicationBuilder.class},
                {Room.TABLE_NAME, RoomBuilder.class},
                {RoomDto.TABLE_NAME, RoomDtoBuilder.class},
                {ApplicationBillDto.TABLE_NAME, ApplicationBillDtoBuilder.class},
                {ApplicationDto.TABLE_NAME, ApplicationDtoBuilder.class}
        };
    }

    @Test(dataProvider = "testParameters")
    public void shouldReturnConcreteBuilder(String tableName, Class expected) {
        Builder builder = BuilderFactory.create(tableName);
        Class actual = builder.getClass();
        Assert.assertEquals(actual, expected);
    }
}