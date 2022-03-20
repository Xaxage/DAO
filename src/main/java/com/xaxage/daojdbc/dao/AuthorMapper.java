package com.xaxage.daojdbc.dao;

import com.xaxage.daojdbc.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//This populates ResultSet with data automatically. We just populate our object with ResultSets data
public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Author author = new Author();
//        author.setId(rs.getLong("id"));
//        author.setFirstName(rs.getString("first_name"));
//        author.setLastName(rs.getString("last_name"));

        Author author = Author.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .build();

        return author;
    }
}
