package com.alloiz.palma.server.model;

import javax.persistence.Entity;

@Entity
public class CallbackCounter extends BaseEntity<CallbackCounter> {

    private Long numberOfCallbacks;

    public CallbackCounter() {
    }

    public Long getNumberOfCallbacks() {
        return numberOfCallbacks;
    }

    public CallbackCounter setNumberOfCallbacks(Long numberOfCallbacks) {
        this.numberOfCallbacks = numberOfCallbacks;
        return this;
    }

    @Override
    public String toString() {
        return "CallbackCounter{" +
                "numberOfCallbacks=" + numberOfCallbacks +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
