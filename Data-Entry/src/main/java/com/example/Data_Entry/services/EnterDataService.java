package com.example.Data_Entry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class EnterDataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createTableIfNotExist() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS grades (id INT AUTO_INCREMENT PRIMARY KEY, studentName VARCHAR(255), grade DOUBLE NOT NULL)");
    }

    public void enterGrade(String studentName , double grade) {
        createTableIfNotExist();
        jdbcTemplate.update("INSERT INTO grades (studentName, grade) VALUES (?, ?)", studentName, grade);
    }
}
