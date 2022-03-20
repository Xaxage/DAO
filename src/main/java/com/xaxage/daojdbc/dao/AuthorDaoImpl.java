package com.xaxage.daojdbc.dao;

import com.xaxage.daojdbc.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(), id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name = ? AND last_name = ?",
                getRowMapper(), firstName, lastName);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO author (first_name, last_name) VALUES (?,?)"
                , author.getFirstName(), author.getLastName());

        //This SQL is MySQL specific.
        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(createdId);
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update("UPDATE  author SET first_name = ?, last_name = ? WHERE id = ?"
                , author.getFirstName(), author.getLastName(), author.getId());

        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        jdbcTemplate.update("DELETE FROM author WHERE id = ?",id);
    }

    //Each time we create new object, cause this is not thread safe, so it's better to create new object each time
    private RowMapper<Author> getRowMapper() {
        return new AuthorMapper();
    }
}
