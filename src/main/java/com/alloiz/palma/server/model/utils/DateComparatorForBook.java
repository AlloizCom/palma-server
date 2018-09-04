package com.alloiz.palma.server.model.utils;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Tariff;

import java.util.Comparator;

public class DateComparatorForBook implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1 == null || o2 == null)
            return 0;
        if(o1.getDateIn()==null || o2.getDateIn()==null)
            return 0;
        if (o1.getDateIn().toLocalDateTime().isBefore(o2.getDateIn().toLocalDateTime())) {
            return 1;
        } else if (o1.getDateIn().toLocalDateTime().isAfter(o2.getDateIn().toLocalDateTime()))
            return -1;
        return 0;
    }
}
