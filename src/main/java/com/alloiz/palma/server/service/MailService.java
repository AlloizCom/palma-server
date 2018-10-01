package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Callback;
import com.alloiz.palma.server.model.enums.Language;

import java.util.Map;

public interface MailService {

    void sendBookMailForStuffAndUser(Book book, Language language);
}
