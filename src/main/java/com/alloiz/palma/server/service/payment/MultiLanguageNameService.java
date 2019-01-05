package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.payment.MultiLanguageName;

import java.util.List;


public interface MultiLanguageNameService extends CRUDService<MultiLanguageName>
{

    List<MultiLanguageName> findAllAvailable();
}
