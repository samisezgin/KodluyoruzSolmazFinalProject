package com.samisezgin.finalproject.model;

import com.samisezgin.finalproject.model.enums.RoleName;

import javax.persistence.Entity;

@Entity
public class AdminUser extends User{

    public AdminUser()
    {
        this.getRole().setRoleName(RoleName.ADMIN);
    }
}
