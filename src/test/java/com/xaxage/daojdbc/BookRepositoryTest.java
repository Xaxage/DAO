package com.xaxage.daojdbc;

import com.xaxage.daojdbc.domain.Book;
import com.xaxage.daojdbc.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.xaxage.daojdbc.dao"})//@DataJpaTest doesn't bring whole project, so we add it separately
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//SO Spring won't bring H2
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testEmptyResultException() {

        assertThrows(EmptyResultDataAccessException.class, () -> {
            Book book = bookRepository.readByTitle("foobar4");
        });
    }

    @Test
    void testNullParam() {
        assertNull(bookRepository.getByTitle(null));
    }

    @Test
    void testNoException() {

        assertNull(bookRepository.getByTitle("foo"));
    }

    @Test
    void testBookStream() {
        AtomicInteger counter = new AtomicInteger();

        bookRepository.findAllByTitleNotNull().forEach(book -> {
            counter.incrementAndGet();
        });

        assertThat(counter.get()).isGreaterThan(5);
    }

    @Test
    void testBookQuery() {
        Book book = bookRepository.findBookByTitleWithQuery("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testBookQueryNamed() {
        Book book = bookRepository.findBookByTitleWithQueryNamed("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testBookQueryNative() {
        Book book = bookRepository.findBookByTitleWithNativeQuery("Clean Code");

        assertThat(book).isNotNull();
    }
}
