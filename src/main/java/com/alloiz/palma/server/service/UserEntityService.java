package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.UserEntity;

import java.util.List;

public interface UserEntityService {
    UserEntity findOneAvailable(Long id);

    List<UserEntity> findAllAvailable();

    UserEntity findOne(Long id);

    List<UserEntity> findAll();

    UserEntity save(UserEntity userEntity);

    UserEntity update(UserEntity update);

    Boolean delete(Long id);

    Boolean createDefaultUser();

    UserEntity findByLogin(String login);
}
