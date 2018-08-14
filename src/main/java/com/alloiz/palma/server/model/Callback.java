package com.alloiz.palma.server.model;

import javax.persistence.Entity;

@Entity
public class Callback extends BaseEntity<Callback> {

    private String title;
    private String Message;
    private String email;
    private String phone;

    public Callback() {
    }

    public String getTitle() {
        return title;
    }

    public Callback setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return Message;
    }

    public Callback setMessage(String message) {
        Message = message;
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
                "title='" + title + '\'' +
                ", Message='" + Message + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

