package com.alloiz.palma.server.service.utils;


import com.alloiz.palma.server.model.Book;

import java.util.HashMap;
import java.util.Map;

public class MailChecker {

    public static Map<String, Object> checkNull(Book book){
        Map<String, Object> map = new HashMap<>();
        if (book.getLastName() != null){
            map.put("lastName", book.getLastName());
        } else {
            map.put("lastName", "-");
        }
        if (book.getFirstName() != null){
            map.put("firstName", book.getFirstName());
        }else {
            map.put("firstName", "-");
        }
        if (book.getPhoneNumber() != null){
            map.put("phone", book.getPhoneNumber());
        }else {
            map.put("phone", "-");
        }
        if (book.getEmail() != null){
            map.put("email", book.getEmail());
        }else {
            map.put("email", "-");
        }
        if (book.getDateIn() != null){
            map.put("dateIn", book.getDateIn());
        }else {
            map.put("dateIn", "-");
        }
        if (book.getDateOut() != null){
            map.put("dateOut", book.getDateOut());
        }else {
            map.put("dateOut", "-");
        }
        if (book.getRoomType() != null){
            map.put("roomType", book.getRoomType());
        }else {
            map.put("roomType", "-");
        }
        if (book.getAmountOfRooms() != null){
            map.put("amountOfRoom", book.getAmountOfRooms());
        }else {
            map.put("amountOfRoom", "-");
        }
        if (book.getAdults() != null){
            map.put("adults", book.getAdults());
        }else {
            map.put("adults", "-");
        }
        if (book.getKids() != null){
            map.put("kids", book.getKids());
        }else {
            map.put("kids", "-");
        }
        if (book.getMessage() != null){
            map.put("message", book.getMessage());
        }else {
            map.put("message", "-");
        }
        if (book.getBookingDay() != null){
            map.put("bookingDay", book.getBookingDay());
        }else {
            map.put("bookingDay", "-");
        }
        if (book.getOrderStatus() != null){
            map.put("orderStatus", book.getOrderStatus());
        }else {
            map.put("orderStatus", "-");
        }
        return map;
    }

    public static String formatDateForTitle (Book book){
        String bookingDate = book.getBookingDay().toString().substring(0,18);
//        String from = book.getDateIn().toString().substring(0,10);
//        String to = book.getDateOut().toString().substring(0,10);
//        String dateForTitle = " ("
//                .concat(bookingDate)
//                .concat(")")
//                .concat(" від ")
//                .concat(from)
//                .concat(" до ")
//                .concat(to);
        return bookingDate;
    }
}
