package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.service.MailService;
import com.alloiz.palma.server.service.PayService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;

public class PayServiceImpl implements PayService {

    private static final Logger logger = Logger.getLogger(PayServiceImpl.class);
    private static final String STATUS = "status";
    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";
    private static final String SANDBOX = "sandbox";
    private static final String ORDER_ID = "order_id";

    @Autowired
    private MailService mailService;


    //todo add properties
    @Value("${liqpay.public.key}")
    private String liqpayPublicKey;
    @Value("${liqpay.private.key}")
    private String liqpayPrivateKey;
    @Value("${base.server.ulr}")
    private String url;


    @Override
    public String getButton() {
        HashMap<String, String> params = new HashMap<>();
//        String uuid = buyTickets(binWrapper).get(0).getOrderId();
        params.put("sandbox", "1");
        params.put("action", "pay");
//        params.put("amount", calcPrice(binWrapper.getSeats()) + "");
        params.put("currency", "UAH");
        params.put("description", "Ticket buying");
//        params.put("result_url", url + "/payment/server/" + uuid);//forward after click 'back'
//        params.put("server_url", url + "/payment/server/" + uuid);
//        params.put("order_id", uuid);
        params.put("version", "3");
//        params.put("expired_date", getExpiresIn(uuid));
//        return new StringWrapper().setValue(new LiqPay(liqpayPublicKey, liqpayPrivateKey).cnb_form(params));
        return null;
    }

    @Override
    public void buy() {
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
//        seatService.findAllByTicketOrderId(orderID).forEach(seat -> seatService.update(seat.getId(), Bought.AVAILABLE));
//        logger.info("revertPayment:[" + orderID + "]");
//        historyService.save("revertPayment:[" + orderID + "]", "PayService");//todo
    }

    @Override
    public void completePayment(String orderID) {
//        List<Ticket> tickets = ticketService.findAllByOrderId(orderID);
//        mailService.sendTickets(tickets.get(0).getUser(), orderID);
//        logger.info("completePayment:[" + orderID + "]");
//        historyService.save("completePayment:[" + orderID + "]", "PayService");//todo
//        seatService.findAllByTicketOrderId(orderID).forEach(seat -> seatService.update(seat.getId(), Bought.BOUGHT));
    }


    private String getExpiresIn(String uuid) {
//        List<Seat> seatList = seatService.findAllByTicketOrderId(uuid);
        LocalDateTime dateTime = LocalDateTime.now().plusDays(3);
//        for (Seat seat : seatList) {
//            dateTime = seat.getLockedUntil().isBefore(dateTime) ? seat.getLockedUntil() : dateTime;
//        }
//        logger.info(dateTime.atZone(ZoneId.of("UTC")).minusHours(2).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
        return dateTime.atZone(ZoneId.of("UTC")).minusHours(2).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    }

}
