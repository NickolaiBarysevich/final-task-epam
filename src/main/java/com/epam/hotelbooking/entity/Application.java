package com.epam.hotelbooking.entity;


import java.sql.Date;
import java.util.Objects;

public class Application implements Identifiable {

    public static final String TABLE_NAME = "application";

    public static final String ID = "application.id";
    public static final String PLACES = "places";
    public static final String CHECK_IN_DATE = "check_in_date";
    public static final String CHECK_OUT_DATE = "check_out_date";
    public static final String STATUS = "status";
    public static final String CLIENT_ID = "client_id";
    public static final String ROOM_CLASS_ID = "room_class_id";


    private final Long id;
    private final Integer places;
    private final Date checkInDate;
    private final Date checkOutDate;
    private ApplicationStatus applicationStatus;
    private final Long clientId;
    private final Long roomClassId;

    public Application(Long id, Integer places, Date checkInDate, Date checkOutDate, ApplicationStatus applicationStatus,
                       Long clientId, Long roomClassId) {
        this.id = id;
        this.places = places;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.applicationStatus = applicationStatus;
        this.clientId = clientId;
        this.roomClassId = roomClassId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getPlaces() {
        return places;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Long getClientId() {
        return clientId;
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
        Application that = (Application) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(places, that.places) &&
                Objects.equals(checkInDate, that.checkInDate) &&
                Objects.equals(checkOutDate, that.checkOutDate) &&
                applicationStatus == that.applicationStatus &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(roomClassId, that.roomClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, places, checkInDate, checkOutDate, applicationStatus, clientId, roomClassId);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", places=" + places +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", applicationStatus=" + applicationStatus +
                ", clientId=" + clientId +
                ", roomClassId=" + roomClassId +
                '}';
    }
}
