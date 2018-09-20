package com.alloiz.palma.server.model;

import javax.persistence.Entity;

@Entity
public class CallbackCounter extends BaseEntity<CallbackCounter> {

    private Long numberOfBooking;

    public CallbackCounter() {
    }

    public Long getNumberOfBooking() {
        return numberOfBooking;
    }

    public CallbackCounter setNumberOfBooking(Long numberOfBooking) {
        this.numberOfBooking = numberOfBooking;
        return this;
    }

    @Override
    public String toString() {
        return "CallbackCounter{" +
                "numberOfBooking=" + numberOfBooking +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
