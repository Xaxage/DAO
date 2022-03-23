package com.xaxage.daojdbc.dao;


import com.xaxage.daojdbc.domain.Book;

import java.util.List;


public interface BookDao {
    List<Book> findAll();

    Book findByISBN(String isbn);

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}
