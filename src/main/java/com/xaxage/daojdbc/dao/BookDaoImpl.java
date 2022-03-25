package com.xaxage.daojdbc.dao;

import com.xaxage.daojdbc.domain.Book;
import com.xaxage.daojdbc.repositories.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Component
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        Book fetched = bookRepository.getById(book.getId());
        fetched.setTitle(book.getTitle());
        fetched.setIsbn(book.getIsbn());
        fetched.setPublisher(book.getPublisher());
        return bookRepository.save(fetched);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
