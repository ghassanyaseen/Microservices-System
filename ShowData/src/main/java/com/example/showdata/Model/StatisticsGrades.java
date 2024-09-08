package com.example.showdata.Model;

import lombok.*;

@Getter
public class StatisticsGrades {
    private double averageGrade;
    private int minGrade;
    private int maxGrade;

    @Override
    public String toString() {
        return "DataAnalytics{" +
                ", maxGrade=" + maxGrade +
                ", minGrade=" + minGrade +
                ", averageGrade=" + averageGrade +
                '}';
    }

}
