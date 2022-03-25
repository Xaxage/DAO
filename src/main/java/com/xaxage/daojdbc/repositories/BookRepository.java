package com.xaxage.daojdbc.repositories;

import com.xaxage.daojdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findBookByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Book findBookByTitleWithQuery(String title);

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findBookByTitleWithQueryNamed(@Param("title") String title);

    @Query(value = "SELECT * FROM Book WHERE title = :title",nativeQuery = true)
    Book findBookByTitleWithNativeQuery(@Param("title") String title);
}
