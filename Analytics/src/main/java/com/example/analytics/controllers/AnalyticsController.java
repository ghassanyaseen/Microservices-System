package com.example.analytics.controllers;

import com.example.analytics.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/runAnalytics")
    @ResponseBody
    public String runAnalytics() {
        analyticsService.statisticsAnalytics();
        return "Analytics data processed and stored successfully.";
    }
}
