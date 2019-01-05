package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.payment.Client;
import com.alloiz.palma.server.model.payment.Room;

import java.util.List;

@Dto
public class BookFullDto extends BookShortDto<BookFullDto>{

    private Client client;

    private List<Room> rooms;


    public BookFullDto() {
    }

    public Client getClient() {
        return client;
    }

    public BookFullDto setClient(Client client) {
        this.client = client;
        return this;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public BookFullDto setRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    @Override
    public String toString() {
        return "BookFullDto{" +
                "client=" + client +
                ", rooms=" + rooms +
                ", dateFrom=" + dateFrom +
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
