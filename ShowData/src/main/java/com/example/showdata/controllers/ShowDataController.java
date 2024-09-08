package com.example.showdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.showdata.services.ShowDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import com.example.showdata.Model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("validUser")
public class ShowDataController {

    @Autowired
    private ShowDataService showDataService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Users user = new Users(username, password);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(
                "http://my-authentication:8081/authentication/login",
                user,
                Boolean.class);

        boolean isValid = Boolean.TRUE.equals(response.getBody());

        if (isValid) {
            model.addAttribute("validUser", true);
            return "redirect:/showData";
        }
        model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
        return "login";
    }

    @GetMapping("/showData")
    public String showData(Model model, @SessionAttribute("validUser") Boolean validUser) {

        if (Boolean.TRUE.equals(validUser)) {

            List<StatisticsGrades> statisticsGradesList = showDataService.getStatisticsGrades();

            model.addAttribute("statisticsGrades", statisticsGradesList);

            return "statisticsView";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("validUser", null);
        return "redirect:/";
    }
}
