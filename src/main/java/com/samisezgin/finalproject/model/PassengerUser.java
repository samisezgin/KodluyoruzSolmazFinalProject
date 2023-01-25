package com.samisezgin.finalproject.model;

import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;
import com.samisezgin.finalproject.model.enums.RoleName;

import javax.persistence.*;
import java.util.List;

@Entity
public class PassengerUser extends User{

    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "passengerUser",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Booking> bookingList;

    public PassengerUser()
    {
        this.getRole().setRoleName(RoleName.PASSENGER);
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

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
