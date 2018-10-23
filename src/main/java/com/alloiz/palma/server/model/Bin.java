package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateDeserializerForBook;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.alloiz.palma.server.model.utils.DateSerializerForBook;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Entity
public class Bin extends BaseEntity<Bin> {

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Timestamp bookingDay;
    private Timestamp dateIn;
    private Timestamp dateOut;
    private String uuid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Integer adults;
    private Integer kids;

    public Bin() {
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Bin setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    @JsonSerialize(using = DateSerializerForBook.class)
    public Timestamp getBookingDay() {
        return bookingDay;
    }

    @JsonDeserialize(using = DateDeserializerForBook.class)
    public Bin setBookingDay(Timestamp bookingDay) {
        this.bookingDay = bookingDay;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateIn() {
        return dateIn;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Bin setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateOut() {
        return dateOut;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Bin setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Bin setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Bin setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Bin setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Bin setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Bin setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAdults() {
        return adults;
    }

    public Bin setAdults(Integer adults) {
        this.adults = adults;
        return this;
    }

    public Integer getKids() {
        return kids;
    }

    public Bin setKids(Integer kids) {
        this.kids = kids;
        return this;
    }

    @Override
    public String toString() {
        return "Bin{" +
                "orderStatus=" + orderStatus +
                ", bookingDay=" + bookingDay +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", uuid='" + uuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", adults=" + adults +
                ", kids=" + kids +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
