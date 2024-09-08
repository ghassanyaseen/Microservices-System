package com.example.analytics.service;

import com.example.analytics.model.DataAnalytics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class AnalyticsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String UNIQUE_ID = "unique_stat_record_id";

    @Scheduled(fixedRate = 5000) // 5000 milliseconds = 5 seconds
    public void statisticsAnalytics() {

        // Retrieve results from the database
        Double maxGrade = jdbcTemplate.queryForObject("SELECT MAX(grade) FROM grades", Double.class);
        Double minGrade = jdbcTemplate.queryForObject("SELECT MIN(grade) FROM grades", Double.class);
        Double avgGrade = jdbcTemplate.queryForObject("SELECT AVG(grade) FROM grades", Double.class);

        // Define query to find the existing statistics document
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(UNIQUE_ID));

        // Define the update operation
        Update update = new Update();
        update.set("maxGrade", maxGrade != null ? maxGrade : 0.0);
        update.set("minGrade", minGrade != null ? minGrade : 0.0);
        update.set("averageGrade", avgGrade != null ? avgGrade : 0.0);

        // update if the document exists, otherwise insert a new one
        mongoTemplate.upsert(query, update, DataAnalytics.class, "statistics");

        System.out.println("Updated Max Grade: " + maxGrade);
    }
}
