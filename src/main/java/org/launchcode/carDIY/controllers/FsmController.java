package org.launchcode.carDIY.controllers;

import org.launchcode.carDIY.data.CarInDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("fsm")
public class FsmController {

    @Autowired
    private CarInDBRepository carInDBRepository;

    @GetMapping()
    public String chooseFSMbranch(Model model) {
            model.addAttribute("title", "Factory Service Manuals");
            model.addAttribute("carSInDB", carInDBRepository.findAll());

            return "fsm/index";

    }



}
