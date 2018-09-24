package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Callback;
import com.alloiz.palma.server.service.utils.MailContentBuilder;
import com.alloiz.palma.server.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    private static final String ADMIN_MAIL = "admin@gmail.com";
    private static final String MODERATOR_MAIL = "moderator@gmail.com";
    private static final String TITLE = "Palma Hotel Lviv";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    private String send(String mail, String title, String template, Map<String, Object> model) {
        String text = mailContentBuilder.getFreeMarkerTemplateContent(template, model);
        mailSender.send(mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setTo(mail);
            messageHelper.setSubject(title);
            messageHelper.setText(text, true);
        });
        return text;
    }

    @Override
    public Callback sendCallback(Callback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", callback.getEmail());
        map.put("phone", callback.getPhone());
        map.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy о hh:mm:ss")));
        send("some@gmail.com", "Title", "supportAdmin.html", map);
        send(callback.getEmail(), "Title", "supportUser.html", map);
        return callback;
    }

    @Override
    public String getAdminMail(){
        return ADMIN_MAIL;
    }

    @Override
    public String getModeratorMail(){
        return MODERATOR_MAIL;
    }

    @Override
    public void sendCallbackMailForStuff(Callback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", callback.getEmail());
        map.put("phone", callback.getPhone());
        map.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy о hh:mm:ss")));
        map.put("message",callback.getMessage());
        send(ADMIN_MAIL,TITLE,"supportAdmin.html",map);
        send(MODERATOR_MAIL,TITLE,"supportAdmin.html",map);
    }

    @Override
    public void sendBookMailForStuffAndUser(Book book){
        Map<String, Object> map = new HashMap<>();
        map.put("lastName", book.getLastName());
        map.put("firstName", book.getFirstName());
        map.put("phone", book.getPhoneNumber());
        map.put("email", book.getEmail());
        map.put("dateIn", book.getDateIn());
        map.put("dateOut", book.getDateOut());
        map.put("roomType", book.getRoomType());
        map.put("amountOfRoom", book.getAmountOfRooms());
        map.put("adults", book.getAdults());
        map.put("kids", book.getKids());
        map.put("message", book.getMessage());
        send(ADMIN_MAIL,TITLE,"bookForStuff.html",map);
        send(MODERATOR_MAIL,TITLE,"bookForStuff.html",map);
        send(book.getEmail(),TITLE,"bookForUser.html",map);
    }

}
