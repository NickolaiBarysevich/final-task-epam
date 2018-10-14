package com.epam.hotelbooking.entity.dto;

import com.epam.hotelbooking.entity.Identifiable;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class ApplicationDto implements Identifiable {

    public static final String TABLE_NAME = "application_user_bill_room_class";

    private final Long id;
    private final String username;
    private final String fullName;
    private final String phoneNumber;
    private final Integer places;
    private final Date checkIn;
    private final Date checkOut;
    private final String roomClass;
    private final Long roomId;
    private final BigDecimal cost;
    private final Long clientId;

    public ApplicationDto(Long id, String username, String fullName, String phoneNumber,
                          Integer places, Date checkIn, Date checkOut, String roomClass, Long roomId,
                          BigDecimal cost, Long clientId) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.places = places;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomClass = roomClass;
        this.roomId = roomId;
        this.cost = cost;
        this.clientId = clientId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getPlaces() {
        return places;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public Long getRoomId() {
        return roomId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Long getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationDto that = (ApplicationDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(places, that.places) &&
                Objects.equals(checkIn, that.checkIn) &&
                Objects.equals(checkOut, that.checkOut) &&
                Objects.equals(roomClass, that.roomClass) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName, phoneNumber, places, checkIn,
                checkOut, roomClass, roomId, cost, clientId);
    }

    @Override
    public String toString() {
        return "ApplicationDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", places=" + places +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", roomClass='" + roomClass + '\'' +
                ", roomId=" + roomId +
                ", cost=" + cost +
                ", clientId=" + clientId +
                '}';
    }
}
