package org.kd.ms.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JournalService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertData(){
        System.out.println("> Table creation");
        jdbcTemplate.execute("DROP TABLE JOURNAL IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE JOURNAL(id SERIAL, title VARCHAR(255), summary VARCHAR(255), created TIMESTAMP)");
        System.out.println("> Inserting data...");
        jdbcTemplate.execute("INSERT INTO JOURNAL(title,summary,created) VALUES('Get to know Spring Boot','Today I will learn Spring Boot', '2016-01-01 00:00:00.00')");
        jdbcTemplate.execute("INSERT INTO JOURNAL(title,summary,created) VALUES('Simple Spring Boot Project','I will do my first Spring Boot project','2016-01-02 00:00:00.00')");
        jdbcTemplate.execute("INSERT INTO JOURNAL(title,summary,created) VALUES('Spring Boot Reading','Read more about Spring Boot', '2016-02-01 00:00:00.00')");
        jdbcTemplate.execute("INSERT INTO JOURNAL(title,summary,created) VALUES('Spring Boot in the Cloud','Learn Spring Boot using Cloud Foundry','2016-01-01 00:00:00.00')");
        System.out.println("> Done.");
    }
}
