package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.payment.Language;

import java.util.List;


public interface PaymentLanguageService extends CRUDService<Language>
{
    Language findOneAvailable(Long id);

    List<Language> findAllAvailable();
}
