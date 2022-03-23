package com.xaxage.daojdbc.dao;

import com.xaxage.daojdbc.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getAuthorByNameNative(String firstName,String lastName);

    Author findAuthorByNameCriteria(String firstName,String lastName);

    List<Author> findAll();

    List<Author> listAuthorByLastNameLike(String lastName);

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
