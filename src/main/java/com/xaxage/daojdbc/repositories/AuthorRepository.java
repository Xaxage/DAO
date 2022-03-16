package com.xaxage.daojdbc.repositories;

import com.xaxage.daojdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
