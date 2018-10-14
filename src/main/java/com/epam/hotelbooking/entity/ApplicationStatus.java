package com.epam.hotelbooking.entity;

public enum ApplicationStatus {

    CONSIDERING("considering"),
    APPROVED("approved"),
    EXPIRED("expired"),
    CANCELED("canceled"),
    PAID("paid");

    private String value;

    ApplicationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
