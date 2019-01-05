package com.alloiz.palma.server.repository.payment;

import com.alloiz.palma.server.model.payment.MultiLanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MultiLanguageNameRepository extends JpaRepository<MultiLanguageName, Long>
{
    List<MultiLanguageName> findAllByAvailable(Boolean available);
}
