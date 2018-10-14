package com.epam.hotelbooking.entity.dto;

import com.epam.hotelbooking.entity.Identifiable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class RoomDto implements Identifiable {

    public static final String TABLE_NAME = "room_room_class";

    private final Long roomId;
    private final Integer beds;
    private final String roomClass;
    private final BigDecimal price;

    public RoomDto(Long roomId, Integer beds, String roomClass, BigDecimal price) {
        this.roomId = roomId;
        this.beds = beds;
        this.roomClass = roomClass;
        this.price = price;
    }

    @Override
    public Long getId() {
        return roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Integer getBeds() {
        return beds;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDto that = (RoomDto) o;
        return Objects.equals(roomId, that.roomId) &&
                Objects.equals(beds, that.beds) &&
                Objects.equals(roomClass, that.roomClass) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, beds, roomClass, price);
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "roomId=" + roomId +
                ", beds=" + beds +
                ", roomClass='" + roomClass + '\'' +
                ", price=" + price +
                '}';
    }
}
