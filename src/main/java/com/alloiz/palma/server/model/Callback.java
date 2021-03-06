package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Callback extends BaseEntity<Callback> {

    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String message;

    private String email;
    private String phone;
    private Timestamp dateTime;

    public Callback() {
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateTime() {
        return dateTime;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Callback setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getName() {
        return name;
    }

    public Callback setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Callback setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Callback setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Callback setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "Callback{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateTime=" + dateTime +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}

