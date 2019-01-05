package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.payment.Language;
import com.alloiz.palma.server.repository.payment.LanguageRepository;
import com.alloiz.palma.server.service.payment.LanguageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.checkId;
import static com.alloiz.palma.server.service.utils.Validation.checkObjectExistsById;
import static com.alloiz.palma.server.service.utils.Validation.checkSave;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    private static final Logger LOGGER = Logger.getLogger(LanguageServiceImpl.class);

    @Override
    public Language findOne(Long id) {
        checkId(id);
        return languageRepository.findOne(id);
    }

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language findOneAvailable(Long id) {
        checkId(id);
        return languageRepository.findByAvailableAndId(true,id);
    }

    @Override
    public List<Language> findAllAvailable() {
        return languageRepository.findAllByAvailable(true);
    }

    @Override
    public Language save(Language language) {
        checkSave(language);
        language.setAvailable(true);
        LOGGER.info(language);
        return languageRepository.save(language);
    }

    @Override
    public Language update(Language language) {
        checkObjectExistsById(language.getId(), languageRepository);
        LOGGER.info(language);
        return  languageRepository.save(findOne(language.getId())
                .setAvailable(language.getAvailable())
                .setLanguagesName(language.getLanguagesName())
        );
    }

    @Override
    public Boolean delete(Language obj) {
        Long id = obj.getId();
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            languageRepository.delete(checkObjectExistsById(id, languageRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            languageRepository.delete(checkObjectExistsById(id, languageRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
