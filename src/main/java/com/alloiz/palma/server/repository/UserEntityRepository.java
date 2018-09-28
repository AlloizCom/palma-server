package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findAllByAvailable(Boolean available);

    UserEntity findByAvailableAndId(Boolean available, Long id);

    UserEntity findByLogin(String login);
}
