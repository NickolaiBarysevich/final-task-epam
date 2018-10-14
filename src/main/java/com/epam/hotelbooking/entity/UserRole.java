package com.epam.hotelbooking.entity;

public enum UserRole {
    CLIENT("client"),
    ADMINISTRATOR("administrator");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
