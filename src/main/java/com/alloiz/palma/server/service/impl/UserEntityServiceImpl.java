package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.UserEntity;
import com.alloiz.palma.server.repository.UserEntityRepository;
import com.alloiz.palma.server.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findOneAvailable(Long id) {
        checkId(id);
        return userEntityRepository.findByAvailableAndId(true,id);
    }

    @Override
    public List<UserEntity> findAllAvailable() {
        return userEntityRepository.findAllByAvailable(true);
    }

    @Override
    public UserEntity findOne(Long id) {
        checkId(id);
        return userEntityRepository.findOne(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userEntityRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        checkSave(userEntity);
        return userEntityRepository.save(userEntity.setAvailable(true)
                    .setPassword(passwordEncoder.encode(userEntity.getPassword())));
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        checkObjectExistsById(userEntity.getId(),userEntityRepository);
        return userEntityRepository.save(findOne(userEntity.getId())
                .setAvailable(userEntity.getAvailable())
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setRole(userEntity.getRole()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            userEntityRepository.delete(checkObjectExistsById(id, userEntityRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
