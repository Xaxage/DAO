package com.xaxage.daojdbc.repositories;

import com.xaxage.daojdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
