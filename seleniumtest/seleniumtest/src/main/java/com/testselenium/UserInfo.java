package com.testselenium;

public class UserInfo {
    String name;
    String roll;
    String email;

    public UserInfo() {
    }

    public UserInfo(String name, String roll, String email) {
        this.name = name;
        this.roll = roll;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", roll='" + roll + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}