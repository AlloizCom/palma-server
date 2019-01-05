package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.payment.MultiLanguageName;
import com.alloiz.palma.server.repository.payment.MultiLanguageNameRepository;
import com.alloiz.palma.server.service.payment.MultiLanguageNameService;
import com.alloiz.palma.server.service.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MultiLanguageNameServiceImpl implements MultiLanguageNameService {

    @Autowired
    private MultiLanguageNameRepository multiLanguageNameRepository;

    @Override
    public MultiLanguageName save(MultiLanguageName obj) {
        Validation.checkSave(obj);
        return multiLanguageNameRepository.save(obj);
    }

    @Override
    public MultiLanguageName update(MultiLanguageName obj) {
        Validation.checkSave(obj);
        Validation.checkId(obj.getId());
        return save(findOne(obj.getId())
                .setName(obj.getName())
                .setLanguage(obj.getLanguage())
                .setAvailable(obj.getAvailable())
        );
    }

    @Override
    public Boolean delete(MultiLanguageName obj) {
        Validation.checkId(obj.getId());
        try{
            multiLanguageNameRepository.delete(obj);
            return true;
        }
        catch (Exception e )
        {
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Validation.checkId(id);
        try{
            multiLanguageNameRepository.delete(id);
            return true;
        }
        catch (Exception e )
        {
            return false;
        }
    }

    @Override
    public MultiLanguageName findOne(Long id) {
        return multiLanguageNameRepository.findOne(id);
    }

    @Override
    public List<MultiLanguageName> findAll() {
        return multiLanguageNameRepository.findAll();
    }
}
