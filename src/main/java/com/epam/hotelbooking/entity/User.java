package com.epam.hotelbooking.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User implements Identifiable {

    public static final String TABLE_NAME = "user";

    public static String ID = "user.id";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String NAME = "name";
    public static String LAST_NAME = "last_name";
    public static String PHONE_NUMBER = "phone_number";
    public static String EMAIL = "email";
    public static String WALLET = "wallet";
    public static String ROLE = "role";

    private final Long id;
    private final String username;
    private final String password;
    private final String name;
    private final String lastName;
    private final String phoneNumber;
    private final String email;
    private BigDecimal wallet;
    private final UserRole role;

    public User(Long id, String username, String password, String name, String lastName,
                String phoneNumber, String email, BigDecimal wallet, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(wallet, user.wallet) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, lastName, phoneNumber, email, wallet, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", wallet=" + wallet +
                ", role=" + role +
                '}';
    }
}

