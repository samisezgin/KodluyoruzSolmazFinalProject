package com.samisezgin.finalproject.dto.request;

import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class UserRequest {

    private String name;
    private String surname;
    private String password;
    private String email;
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public UserRequest() {
    }

    public UserRequest(String name, String surname, String password, String email, String phoneNumber, PassengerType passengerType, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passengerType = passengerType;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
