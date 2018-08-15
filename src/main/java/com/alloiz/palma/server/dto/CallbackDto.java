package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

import java.sql.Timestamp;

@Dto
public class CallbackDto {

    protected Long id;
    protected String name;
    protected String Message;
    protected String email;
    protected String phone;
    protected Timestamp dateTime;
    protected Boolean available;

    public CallbackDto() {
    }

    public Long getId() {
        return id;
    }

    public CallbackDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CallbackDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return Message;
    }

    public CallbackDto setMessage(String message) {
        Message = message;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CallbackDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CallbackDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public CallbackDto setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CallbackDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "CallbackDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Message='" + Message + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateTime=" + dateTime +
                ", available=" + available +
                '}';
    }
}
