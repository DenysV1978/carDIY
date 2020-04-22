package org.launchcode.carDIY.controllers;


import org.launchcode.carDIY.data.CarInDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private CarInDBRepository carInDBRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "Automobile DIYer Database");
        return "index";
    }

    @GetMapping("history")
    public String history(Model model) {
        model.addAttribute("title", "History of you own maintenances and fixes");

        return "indexHistory";
    }



}

