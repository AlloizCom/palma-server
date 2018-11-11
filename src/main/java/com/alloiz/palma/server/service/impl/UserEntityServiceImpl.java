package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.UserEntity;
import com.alloiz.palma.server.model.enums.Role;
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
        return userEntityRepository.findByAvailableAndId(true, id);
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
        checkObjectExistsById(userEntity.getId(), userEntityRepository);
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

    @Override
    public Boolean createDefaultUser() {
        Boolean existAdmin = false;
        Boolean existModerator = false;
        List<UserEntity> users = userEntityRepository.findAllByAvailable(true);
        for (UserEntity user : users
        ) {
            if (user.getRole().equals(Role.ADMIN)) {
                existAdmin = true;
            }
            if (user.getRole().equals(Role.MODERATOR)) {
                existModerator = true;
            }
        }
        if (!existAdmin || !existModerator) {
            if (!existAdmin) {
                save(new UserEntity()
                        .setFirstName("Admin")
                        .setLastName("Admin")
                        .setLogin("admin")
                        .setPassword("admin")
                        .setRole(Role.ADMIN)
                        .setAvailable(true)
                );
            }
            if (!existModerator) {
                save(new UserEntity()
                        .setFirstName("Moderator")
                        .setLastName("Moderator")
                        .setLogin("moderator")
                        .setPassword("moderator")
                        .setRole(Role.MODERATOR)
                        .setAvailable(true)
                );
            }
            return true;
        }
        return false;
    }

    @Override
    public UserEntity findByLogin(String login) {
        checkString(login);
        return userEntityRepository.findByLogin(login);
    }

    @Override
    public UserEntity updatePassword(UserEntity userEntity) {
        checkObjectExistsById(userEntity.getId(), userEntityRepository);
        return userEntityRepository.save(
                findOne(userEntity.getId())
                        .setPassword(passwordEncoder.encode(userEntity.getPassword()))
        );
    }
}
