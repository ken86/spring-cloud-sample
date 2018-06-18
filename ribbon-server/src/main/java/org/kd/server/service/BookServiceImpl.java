package org.kd.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void initData() {
        jdbcTemplate.execute("DROP TABLE BKD_BOOK IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE BKD_BOOK(id SERIAL, author VARCHAR(255), book_name VARCHAR(255), price DOUBLE)");

        jdbcTemplate.execute("INSERT INTO BKD_BOOK(author, book_name, price) VALUES ('JAVA in PRA 1', 'hk', 10.00)");
        jdbcTemplate.execute("INSERT INTO BKD_BOOK(author, book_name, price) VALUES ('JAVA in PRA 1', 'hk', 10.00)");
        jdbcTemplate.execute("INSERT INTO BKD_BOOK(author, book_name, price) VALUES ('JAVA in PRA 1', 'hk', 10.00)");
    }
}
