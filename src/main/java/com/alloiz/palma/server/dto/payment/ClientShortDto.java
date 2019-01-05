package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class ClientShortDto<T extends ClientShortDto> {

    protected Long id;
    protected Boolean available;
    protected String firstName;
    protected String lastName;
    protected String thirdName;
    protected String phoneNumber;
    protected String email;

    public ClientShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public String getFirstName() {
        return firstName;
    }

    public T setFirstName(String firstName) {
        this.firstName = firstName;
        return (T) this;
    }

    public String getLastName() {
        return lastName;
    }

    public T setLastName(String lastName) {
        this.lastName = lastName;
        return (T) this;
    }

    public String getThirdName() {
        return thirdName;
    }

    public T setThirdName(String thirdName) {
        this.thirdName = thirdName;
        return (T) this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public T setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return (T) this;
    }

    public String getEmail() {
        return email;
    }

    public T setEmail(String email) {
        this.email = email;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    @Override
    public String toString() {
        return "ClientShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
