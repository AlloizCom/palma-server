package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.enums.OrderStatus;

import java.sql.Timestamp;
import java.util.List;

@Dto
public class BinDto {

    protected Long id;
    protected Boolean available;
    protected OrderStatus orderStatus;
    protected Timestamp bookingDay;
    protected Timestamp dateIn;
    protected Timestamp dateOut;
    protected String uuid;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
    protected Integer adults;
    protected Integer kids;
    protected List<Book> books;

    public BinDto() {
    }

    public Long getId() {
        return id;
    }

    public BinDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public BinDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public BinDto setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Timestamp getBookingDay() {
        return bookingDay;
    }

    public BinDto setBookingDay(Timestamp bookingDay) {
        this.bookingDay = bookingDay;
        return this;
    }

    public Timestamp getDateIn() {
        return dateIn;
    }

    public BinDto setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    public Timestamp getDateOut() {
        return dateOut;
    }

    public BinDto setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public BinDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BinDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BinDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BinDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BinDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAdults() {
        return adults;
    }

    public BinDto setAdults(Integer adults) {
        this.adults = adults;
        return this;
    }

    public Integer getKids() {
        return kids;
    }

    public BinDto setKids(Integer kids) {
        this.kids = kids;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public BinDto setBooks(List<Book> books) {
        this.books = books;
        return this;
    }

    @Override
    public String toString() {
        return "BinDto{" +
                "id=" + id +
                ", available=" + available +
                ", orderStatus=" + orderStatus +
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
                ", books=" + books +
                '}';
    }
}
