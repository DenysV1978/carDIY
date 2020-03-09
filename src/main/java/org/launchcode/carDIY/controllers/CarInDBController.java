package org.launchcode.carDIY.controllers;

import com.sun.xml.bind.v2.TODO;
import org.launchcode.carDIY.data.CarInDBRepository;
import org.launchcode.carDIY.models.CarInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("fsm/addNewCar")
public class CarInDBController {


    //this controller is responsible for working with cars in the part putting of them in FSM part of application

    @Autowired
    private CarInDBRepository carInDBRepository;

    @GetMapping("add")
    public String displayAddNewCarInDBFSMform(Model model) {
        model.addAttribute("newCarInDB", new CarInDB());
        model.addAttribute("title", "Add new car in DB FSM");
        return "fsm/addNewCar/addNewCar";
    }

    @PostMapping("add")
    public String processAddNewCarInDBFSMform(@ModelAttribute CarInDB newCarInDB, Model model) {
        model.addAttribute("title", "Factory Service Manuals");
        carInDBRepository.save(newCarInDB);
        model.addAttribute("carSInDB", carInDBRepository.findAll());
        return "fsm/index";
    }
    //TODO: implement Edit, Delete for CarInDB





}
