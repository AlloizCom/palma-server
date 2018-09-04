package com.alloiz.palma.server.model.utils;

import com.alloiz.palma.server.model.Tariff;

import java.util.Comparator;

public class DateComparatorForTariff implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
        if (o1 == null || o2 == null)
            return 0;
        if(o1.getDateFrom()==null || o2.getDateFrom()==null)
            return 0;
        if (o1.getDateFrom().toLocalDateTime().isBefore(o2.getDateFrom().toLocalDateTime())) {
            return 1;
        } else if (o1.getDateFrom().toLocalDateTime().isAfter(o2.getDateFrom().toLocalDateTime()))
            return -1;
        return 0;
    }
}
