package com.alloiz.palma.server.service.utils.comparatorsForDate;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Tariff;

public class BookTariffDateChecker {

    public static boolean checkDate(Book book, Tariff tariff){
        if(book.getDateIn().after(tariff.getDateFrom())
                && book.getDateOut().before(tariff.getDateTo())){
            return true;
        } else {
            return false;
        }
    }

}
