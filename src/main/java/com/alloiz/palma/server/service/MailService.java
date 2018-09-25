package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Callback;

public interface MailService {

    Callback sendCallback (Callback callback);

    String getAdminMail();

    String getModeratorMail();

    void sendCallbackLetterForStuff(Callback callback);

    void sendBookMailForStuffAndUser(Book book);
}
