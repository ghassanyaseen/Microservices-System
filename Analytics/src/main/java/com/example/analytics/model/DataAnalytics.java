package com.example.analytics.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "statistics")
public class DataAnalytics {

    @Id
    private String id;

    private double maxGrade;
    private double minGrade;
    private double averageGrade;

    @Override
    public String toString() {
        return "DataAnalytics{" +
                "id='" + id + '\'' +
                ", maxGrade=" + maxGrade +
                ", minGrade=" + minGrade +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
