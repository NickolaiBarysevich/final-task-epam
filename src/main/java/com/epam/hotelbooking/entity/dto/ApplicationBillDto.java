package com.epam.hotelbooking.entity.dto;

import com.epam.hotelbooking.entity.ApplicationStatus;
import com.epam.hotelbooking.entity.Identifiable;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class ApplicationBillDto implements Identifiable {

    public static final String TABLE_NAME = "application_bill";

    private final Long applicationId;
    private final Integer places;
    private final Date checkIn;
    private final Date checkOut;
    private final ApplicationStatus status;

    private final Long roomClassId;
    private final Long billId;
    private final BigDecimal cost;
    private final Long roomId;


    public ApplicationBillDto(Long applicationId, Integer places, Date checkIn, Date checkOut, ApplicationStatus status, Long roomClassId, Long billId, BigDecimal cost, Long roomId) {
        this.applicationId = applicationId;
        this.places = places;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.roomClassId = roomClassId;
        this.billId = billId;
        this.cost = cost;
        this.roomId = roomId;
    }

    @Override
    public Long getId() {
        return applicationId;
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

    public ApplicationStatus getStatus() {
        return status;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getRoomClassId() {
        return roomClassId;
    }

    public Long getBillId() {
        return billId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationBillDto that = (ApplicationBillDto) o;
        return Objects.equals(applicationId, that.applicationId) &&
                Objects.equals(places, that.places) &&
                Objects.equals(checkIn, that.checkIn) &&
                Objects.equals(checkOut, that.checkOut) &&
                status == that.status &&
                Objects.equals(billId, that.billId) &&
                Objects.equals(roomClassId, that.roomClassId) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, places, checkIn, checkOut, status, billId, roomClassId, cost, roomId);
    }

    @Override
    public String toString() {
        return "ApplicationBillDto{" +
                "applicationId=" + applicationId +
                ", places=" + places +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", status=" + status +
                ", roomClassId=" + roomClassId +
                ", billId=" + billId +
                ", cost=" + cost +
                ", roomId=" + roomId +
                '}';
    }
}
