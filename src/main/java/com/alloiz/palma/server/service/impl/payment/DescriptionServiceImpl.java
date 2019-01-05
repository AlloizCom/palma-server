package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.payment.Description;
import com.alloiz.palma.server.repository.payment.DescriptionRepository;
import com.alloiz.palma.server.service.payment.DescriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.checkId;
import static com.alloiz.palma.server.service.utils.Validation.checkObjectExistsById;
import static com.alloiz.palma.server.service.utils.Validation.checkSave;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Override
    public Description save(Description description) {
        checkSave(description);
        description.setAvailable(true);
        LOGGER.info(description);
        return descriptionRepository.save(description);
    }

    @Override
    public Description update(Description description) {
        checkObjectExistsById(description.getId(),descriptionRepository);
        LOGGER.info(description);
        return descriptionRepository.save(findOne(description.getId())
                .setLanguage(description.getLanguage())
                .setText(description.getText())
                .setAvailable(description.getAvailable()));
    }

    @Override
    public Description findOne(Long id) {
        return descriptionRepository.findOne(id);
    }

    @Override
    public List<Description> findAll() {
        return descriptionRepository.findAll();
    }

    @Override
    public Description findOneAvailable(Long id) {
        checkId(id);
        return descriptionRepository.findByAvailableAndId(true,id);
    }

    @Override
    public List<Description> findAllAvailable() {
        return descriptionRepository.findAllByAvailable(true);
    }

    @Override
    public Boolean delete(Description obj) {
        Long id = obj.getId();
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            descriptionRepository.delete(checkObjectExistsById(id, descriptionRepository));
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
            descriptionRepository.delete(checkObjectExistsById(id, descriptionRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
