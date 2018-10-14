package com.epam.hotelbooking.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Bill implements Identifiable {

    public static final String TABLE_NAME = "bill";

    public static final String ID = "bill.id";
    public static final String COST = "cost";
    public static final String ROOM_ID = "room_id";
    public static final String APPLICATION_ID = "application_id";
    public static final String CLIENT_ID = "client_id";

    private final Long id;
    private final BigDecimal cost;
    private final Long roomId;
    private final Long applicationId;
    private final Long clientId;

    public Bill(Long id, BigDecimal cost, Long roomId, Long applicationId, Long clientId) {
        this.id = id;
        this.cost = cost;
        this.roomId = roomId;
        this.applicationId = applicationId;
        this.clientId = clientId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getApplicationId() {
        return applicationId;
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
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id) &&
                Objects.equals(cost, bill.cost) &&
                Objects.equals(roomId, bill.roomId) &&
                Objects.equals(applicationId, bill.applicationId) &&
                Objects.equals(clientId, bill.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, roomId, applicationId, clientId);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", cost=" + cost +
                ", roomId=" + roomId +
                ", applicationId=" + applicationId +
                ", clientId=" + clientId +
                '}';
    }
}
