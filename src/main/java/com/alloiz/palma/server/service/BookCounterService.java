package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.BookCounter;

public interface BookCounterService {

    BookCounter getCounter(Long id);

    BookCounter update(BookCounter counter);

    Boolean delete(Long id);

    Boolean createDefaultCounter();

    BookCounter resetCounter(Long id);

    BookCounter incrementCounter(Long id);
}
