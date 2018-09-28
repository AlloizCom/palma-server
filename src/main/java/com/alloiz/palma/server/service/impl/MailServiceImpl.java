package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.service.utils.MailContentBuilder;
import com.alloiz.palma.server.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

//    private static final String ADMIN_MAIL = "palmalviv@gmail.com";
private static final String ADMIN_MAIL = "bohdanrud5180@gmail.com";
    private static final String TITLE_FOR_ADMINISTRATOR = "Palmahotel.lviv.ua - Нове бронювання!";
    private static final String TITLE_FOR_CLIENT = "Art Hotel “Palma”: бронювання підтверджено!";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    private String sendForClient(String mail, String title, String template, Map<String, Object> model, Boolean forClient) {
        String text = mailContentBuilder
                .getFreeMarkerTemplateContent(template, model);
        mailSender.send(mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(mail);
            messageHelper.setSubject(title);
            messageHelper.setText(text, true);
            if (forClient){
                messageHelper.addInline("palmaLogoImage",
                        new ClassPathResource("/templates/pictures/palma-hotel-logo.jpg"));
                messageHelper.addInline("viber",
                        new ClassPathResource("/templates/pictures/viber.png"));
                messageHelper.addInline("whatsup",
                        new ClassPathResource("/templates/pictures/whatsup.png"));
                messageHelper.addInline("telegram",
                        new ClassPathResource("/templates/pictures/telegram.png"));
            }
        });
        return text;
    }

    @Override
    public void sendBookMailForStuffAndUser(Book book){
        String from = book.getDateIn().toString();
        String to = book.getDateIn().toString();
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
        map.put("bookingDay", book.getBookingDay());
        map.put("orderStatus", book.getOrderStatus());
        sendForClient(ADMIN_MAIL,TITLE_FOR_ADMINISTRATOR + " " + from + " " + to,"letterForAdministrator.html",map,false);
        sendForClient(book.getEmail(),TITLE_FOR_CLIENT,"letterForClient.html",map,true);

    }

}
