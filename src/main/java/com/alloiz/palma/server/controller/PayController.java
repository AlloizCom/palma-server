package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.service.PayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

public class PayController {

//    private static final Logger logger = Logger.getLogger(PayController.class);
//
//    @Autowired
//    private HistoryService historyService;
//
//    @Autowired
//    private PayService payService;
//    @Value("${base.server.ulr}")
//    private String url;
//    @Autowired
//    private ValidService validService;
//    @Autowired
//    private ClientUserService clientUserService;
//
//    @Autowired
//    private CashierService cashierService;
//    @Value("${base.server.redirect-ulr}")
//    private String redirectUrl;
//
//    @PostMapping("/buy-ticket/cashier")
//    private @ResponseBody
//    ResponseEntity<List<TicketFullDTO>> buyCashier(@RequestBody BinWrapper binWrapper, Principal principal) {
//
//        ClientUser clientUser = null;
//        ClientUserDto tempUser = binWrapper.getUser();
//        setUser(binWrapper, principal, tempUser);
//
//
//        if (tempUser != null)
//            if (tempUser.getMail() != null) {
//                binWrapper.getUser().setMail(tempUser.getMail());
//            }
//
//        historyService.save("mail:[" + ofNullable(ofNullable(tempUser).orElse(new ClientUserDto()).getMail()).orElse("null") + "] | count seat:[" + binWrapper.getSeats().size() + "]", "buyCashier");
//        if (binWrapper.getUser() == null)
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        else if (binWrapper.getUser().getMail() == null || binWrapper.getUser().getMail().isEmpty())
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        else if ((Objects.equals(binWrapper.getUser().getRole(), Roles.CASHIER) || Objects.equals(binWrapper.getUser().getRole(), Roles.CASHIER_LEAD))
//                && principal != null && principal.getName() != null)
//            return new ResponseEntity<>(payService.buyTicketsCashier(binWrapper, cashierService.findByMail(principal.getName())).stream().map(ticket -> map(ticket, TicketFullDTO.class)).collect(toList()), HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
//    }
//
//    private void setUser(@RequestBody BinWrapper binWrapper, Principal principal, ClientUserDto tempUser) {
//        ClientUser clientUser;
//        if (principal != null) {
//            if (principal.getName() != null) {
//                if ((clientUser = clientUserService.findByName(principal.getName())) != null) {
//                    binWrapper.setUser(map(clientUser, ClientUserDto.class));
//                }
//            }
//        } else {
//            try {
//                validService.validMail(tempUser.getMail());
//            } catch (Exception e) {
//                binWrapper.setUser(map(clientUserService.findByMail(tempUser.getMail()), ClientUserDto.class));
//            }
//        }
//    }
//    @PostMapping("/get-button")
//    private @ResponseBody
//    ResponseEntity<StringWrapper> getButton(@RequestBody BinWrapper binWrapper, Principal principal) {
//        ClientUser clientUser = null;
//        ClientUserDto tempUser = binWrapper.getUser();
//        setUser(binWrapper, principal, tempUser);
//
//
//        if (tempUser != null)
//            if (tempUser.getMail() != null) {
//                binWrapper.getUser().setMail(tempUser.getMail());
//            }
//
//        historyService.save("mail:[" + ofNullable(ofNullable(tempUser).orElse(new ClientUserDto()).getMail()).orElse("null") + "] | count seat:[" + binWrapper.getSeats().size() + "]", "PayController:[/get-button");
//        if (binWrapper.getUser() == null)
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        else if (binWrapper.getUser().getMail() == null || binWrapper.getUser().getMail().isEmpty())
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        else if (Objects.equals(binWrapper.getUser().getRole(), Roles.CLIENT))
//            return new ResponseEntity<>(payService.getButton(binWrapper), HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
//    }
//
//    //when click to return
//    @GetMapping("/some/{oderId}")
//    private void ssome(@PathVariable String oderId) {
//    }
//
//    //when payed
//    @PostMapping("/server/{oderId}")
//    private String some(@PathVariable String oderId, @RequestParam(required = false) String data, @RequestParam(required = false) String signature) {
//        System.out.println("------------/server/{oderId}------start-----");
//        System.out.println("orderId:[" + oderId + "]");
//        System.out.println("data:[" + data + "]");
//        System.out.println("data:[" + data + "]");
//        System.out.println("signature:[" + signature + "]");
//        System.out.println("------------/server/{oderId}------end-----");
//        payService.checkSuccess(data);
//        historyService.save("orderId:[" + ofNullable(oderId).orElse("null") + "]data:[" + Optional.of(new String(Base64.getDecoder().decode(data))).orElse("null") + "]signature:[" + ofNullable(signature).orElse("null") + "]", "PayController:[/payment/server/{oderId}]");
//        return "redirect:" + redirectUrl + "/#/";
//    }
//
//    @PostMapping("/buy/tickets")
//    private ResponseEntity<List<TicketShortDTO>> buyTickets(@RequestBody List<SeatShortDTO> seatShortDTOS, Principal principal) {
//        return ResponseEntity.ok(payService.buyTickets(seatShortDTOS, principal).stream().map(ticket -> map(ticket, TicketShortDTO.class)).collect(toList()));
//    }
}
