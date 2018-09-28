package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Callback;

public interface MailService {

    void sendBookMailForStuffAndUser(Book book);
}
