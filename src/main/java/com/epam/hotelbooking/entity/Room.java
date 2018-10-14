package com.epam.hotelbooking.entity;

import java.util.*;

public class Room implements Identifiable {

    public static final String TABLE_NAME = "room";

    public static final String ID = "room.id";
    public static final String BEDS = "beds";
    public static final String STATUS = "status";
    public static final String ROOM_CLASS_ID = "room_class_id";

    private final Long id;
    private final Integer beds;
    private RoomStatus status;
    private final Long roomClassId;

    public Room(Long id, Integer beds, RoomStatus status, Long roomClassId) {
        this.id = id;
        this.beds = beds;
        this.status = status;
        this.roomClassId = roomClassId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getBeds() {
        return beds;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Long getRoomClassId() {
        return roomClassId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(beds, room.beds) &&
                Objects.equals(status, room.status) &&
                Objects.equals(roomClassId, room.roomClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beds, status, roomClassId);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", beds=" + beds +
                ", status=" + status +
                ", roomClassId=" + roomClassId +
                '}';
    }
}
