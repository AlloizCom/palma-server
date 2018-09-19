package com.alloiz.palma.server.model;

import javax.persistence.Entity;

@Entity
public class BookCounter extends BaseEntity<BookCounter>{

    private Long numberOfBooking;

    public BookCounter() {
    }

    public Long getNumberOfBooking() {
        return numberOfBooking;
    }

    public BookCounter setNumberOfBooking(Long numberOfBooking) {
        this.numberOfBooking = numberOfBooking;
        return this;
    }

    @Override
    public String toString() {
        return "BookCounter{" +
                "numberOfBooking=" + numberOfBooking +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
