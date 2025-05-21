package com.alquiler.alquiler_app.domain.DTOs;

import java.util.List;

public class PersonRequestDTO {
    private String firstName;
    private String lastName;
    private String idNumber;
    private String phone;
    private String email;
    private String password;

    private boolean admin;
    private boolean provider;
    private List<Long> roleIds;

    public PersonRequestDTO() {
    }

    public PersonRequestDTO(String firstName, String lastName, String idNumber, String phone, String email,
            String password, boolean admin, boolean provider, List<Long> roleIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.provider = provider;
        this.roleIds = roleIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isProvider() {
        return provider;
    }

    public void setProvider(boolean provider) {
        this.provider = provider;
    }
}