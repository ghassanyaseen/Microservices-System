package com.example.Data_Entry.controllers;

import com.example.Data_Entry.Model.Users;
import com.example.Data_Entry.services.EnterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
@SessionAttributes("validUser")
public class DataEntryController {

    @Autowired
    EnterDataService EnteryData;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @GetMapping("/EnterData")
    public String enterDataPage() {
        return "EnterData";
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
            return "redirect:/EnterData";
        }
        model.addAttribute("errorMessage", "Invalid Credentials, Please Try Again.");
        return "login";
    }

    @PostMapping("/EnterData")
    public String submitGrades(
            @RequestParam("studentName") String studentName,
            @RequestParam("grade") double grade,
            Model model) {

        Boolean validUser = (Boolean) model.getAttribute("validUser");
        if (validUser != null && validUser) {
            EnteryData.enterGrade(studentName, grade);

            return "redirect:/EnterData";
        } else {
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("validUser", null);
        return "redirect:/";
    }
}
