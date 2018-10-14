package com.epam.hotelbooking.entity;

public enum RoomStatus {
    BUSY("busy"),
    FREE("free"),
    RESERVED("reserved");

    String value;

    RoomStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
