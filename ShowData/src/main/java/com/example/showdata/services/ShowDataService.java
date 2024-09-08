package com.example.showdata.services;

import com.example.showdata.Model.StatisticsGrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowDataService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<StatisticsGrades> getStatisticsGrades() {
        return mongoTemplate.findAll(StatisticsGrades.class, "statistics");
    }

}