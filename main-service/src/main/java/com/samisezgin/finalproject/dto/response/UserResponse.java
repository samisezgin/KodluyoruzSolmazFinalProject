package com.samisezgin.finalproject.dto.response;

import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserResponse {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

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
