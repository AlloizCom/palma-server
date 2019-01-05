package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.payment.enums.Status;
import java.sql.Timestamp;

@Dto
public class BookShortDto <T extends BookShortDto> {

    protected Timestamp dateFrom;
    protected Timestamp dateTo;
    protected Timestamp bookingDate;
    protected Boolean boughtOnLine;
    protected Double totalPrice;
    protected Double payedPrice;
    protected Boolean isCash;
    protected Status status;
    protected Long id;


    public BookShortDto() {
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public T setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
        return (T) this;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public T setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
        return (T) this;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public T setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
        return (T) this;
    }

    public Boolean getBoughtOnLine() {
        return boughtOnLine;
    }

    public T setBoughtOnLine(Boolean boughtOnLine) {
        this.boughtOnLine = boughtOnLine;
        return (T) this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public T setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return (T) this;
    }

    public Double getPayedPrice() {
        return payedPrice;
    }

    public T setPayedPrice(Double payedPrice) {
        this.payedPrice = payedPrice;
        return (T) this;
    }

    public Boolean getCash() {
        return isCash;
    }

    public T setCash(Boolean cash) {
        isCash = cash;
        return (T) this;
    }

    public Status getStatus() {
        return status;
    }

    public T setStatus(Status status) {
        this.status = status;
        return (T) this;
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    @Override
    public String toString() {
        return "BookShortDto{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", bookingDate=" + bookingDate +
                ", boughtOnLine=" + boughtOnLine +
                ", totalPrice=" + totalPrice +
                ", payedPrice=" + payedPrice +
                ", isCash=" + isCash +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
