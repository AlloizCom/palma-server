package com.alloiz.palma.server.repository.payment;

import com.alloiz.palma.server.model.payment.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentBookRepository extends JpaRepository<Book,Long>
{
    List<Book> findAllByAvailable(Boolean available);

    Book findByAvailableAndId(Boolean available, Long id);
}
