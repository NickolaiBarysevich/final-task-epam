package com.epam.hotelbooking.entity;

public enum RoomClass implements Identifiable {
    STANDARD(1, "standard"),
    COMFORT(2, "comfort"),
    COMFORT_PLUS(3, "comfort+");

    int id;
    String value;

    RoomClass(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Long getId() {
        return (long) id;
    }

    public RoomClass getById(int id) {
        switch (id) {
            case 1:
                return RoomClass.STANDARD;
            case 2:
                return RoomClass.COMFORT;
            case 3:
                return RoomClass.COMFORT_PLUS;
            default:
                throw new IllegalArgumentException("unknown class id");
        }
    }
}
