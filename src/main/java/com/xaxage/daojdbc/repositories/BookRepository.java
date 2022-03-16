package com.xaxage.daojdbc.repositories;

import com.xaxage.daojdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
