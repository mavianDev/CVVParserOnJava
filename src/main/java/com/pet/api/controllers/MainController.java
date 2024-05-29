package com.pet.api.controllers;

import com.pet.api.services.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    VirusDataService virusDataService = new VirusDataService();

    @GetMapping("/")
    public String main (Model model) {
        model.addAttribute("locationStats", virusDataService.getAllStats());
        return "main";
    }
}
