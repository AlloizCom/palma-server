package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.service.*;
import com.alloiz.palma.server.service.utils.liqpay.LiqPay;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;

@Service
public class PayServiceImpl implements PayService {

    private static final Logger logger = Logger.getLogger(PayServiceImpl.class);
    private static final String STATUS = "status";
    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";
    private static final String SANDBOX = "sandbox";
    private static final String ORDER_ID = "order_id";
    private static final String DATE_TIME_PATTERN = "YYYY-MM-dd HH:mm:ss";
    private static final Long EXPIRES_IN_MINUTES = 15L;

    @Autowired
    private MailService mailService;

    @Autowired
    private BookService bookService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TariffService tariffService;

    @Value("${liqpay.public.key}")
    private String liqpayPublicKey;
    @Value("${liqpay.private.key}")
    private String liqpayPrivateKey;
    @Value("${base.server.url}")
    private String url;

    @Override
    public String getButton(Book book) {
        HashMap<String, String> params = new HashMap<>();
        String uuid = book.getUuid();
        params.put("sandbox", "1");
        params.put("action", "pay");
        params.put("amount", 1 + "");
        params.put("currency", "UAH");
        params.put("description", "Ticket buying");
        params.put("result_url", url + "/payment/server/" + uuid);//forward after click 'back'
        params.put("server_url", url + "/payment/server/" + uuid);
        params.put("order_id", uuid);
        params.put("version", "3");
        params.put("expired_date", setExpiresIn());
        return new LiqPay(liqpayPublicKey, liqpayPrivateKey).cnb_form(params);
    }

    @Override
    public Integer setPrice(Book book) {
        return null;
    }

    @Override
    public Boolean checkSuccess(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNodeRoot = objectMapper.readTree(new String(Base64.getDecoder().decode(data)));
            JsonNode jsonNodeStatus = jsonNodeRoot.get(STATUS);
            if (jsonNodeStatus.getTextValue().equals(SUCCESS) || jsonNodeStatus.getTextValue().equals(SANDBOX)) {
                completePayment(jsonNodeRoot.get(ORDER_ID).getTextValue());
            } else {
                revertPayment(jsonNodeRoot.get(ORDER_ID).getTextValue());
            }
            return jsonNodeStatus.getTextValue().equals(SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void revertPayment(String orderID) {
        Book book = bookService.findByUuid(orderID);
        bookService.update(book.setOrderStatus(OrderStatus.CANCELED));
        roomService.addAmount(book.getRoomType(), book.getAmountOfRooms());
        logger.info("revertPayment:[" + orderID + "]");
    }

    @Override
    public void completePayment(String orderID) {
        Book book = bookService.findByUuid(orderID);
//        mailService.sendTickets(tickets.get(0).getUser(), orderID);todo
        bookService.update(book.setOrderStatus(OrderStatus.AVAILABLE));
        logger.info("completePayment:[" + orderID + "]");
    }


    private String setExpiresIn() {
        LocalDateTime dateTime = LocalDateTime.now();
        logger.info(dateTime.atZone(ZoneId.of("UTC")).plusMinutes(EXPIRES_IN_MINUTES).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        return dateTime.atZone(ZoneId.of("UTC")).plusMinutes(EXPIRES_IN_MINUTES).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }

}
