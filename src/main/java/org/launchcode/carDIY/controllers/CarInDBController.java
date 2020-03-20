package org.launchcode.carDIY.controllers;

import com.sun.xml.bind.v2.TODO;
import org.launchcode.carDIY.data.CarInDBRepository;
import org.launchcode.carDIY.models.CarInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fsm/carInDB")
public class CarInDBController {


    //this controller is responsible for working with cars in the part putting of them in FSM part of application

    @Autowired
    private CarInDBRepository carInDBRepository;

    @GetMapping("addNewCar")
    public String displayAddNewCarInDBFSMform(Model model) {
        model.addAttribute("newCarInDB", new CarInDB());
        model.addAttribute("title", "Add new car in DB FSM");
        return "fsm/carInDB/addNewCar";
    }

    @PostMapping("addNewCar")
    public String processAddNewCarInDBFSMform(@ModelAttribute CarInDB newCarInDB, Model model) {
        model.addAttribute("title", "Factory Service Manuals");
        carInDBRepository.save(newCarInDB);
        model.addAttribute("carSInDB", carInDBRepository.findAll());
        System.out.println("Stop");
        return "fsm/index";
    }
    //TODO: implement Edit, Delete for CarInDB

    @GetMapping(value="editCar/{idCar}")
    public String displayEditCarInDBFSMform(@PathVariable int idCar, Model model) {
        model.addAttribute("carInDB", carInDBRepository.findById(idCar));
        model.addAttribute("title", "Edit car in DB FSM");
        return "fsm/carInDb/editCar";
    }

    @PostMapping(value="editCar/{idCar}")
    public String processEditCarInDBform(@PathVariable int idCar, @RequestParam String nameOfCarInDB, String nameOfManufacturer, String modelOfCar, int yearOfManufacturing, Model model) {

        CarInDB car = carInDBRepository.findById(idCar).get();
        car.setModelOfCar(modelOfCar);
        car.setNameOfManufacturer(nameOfManufacturer);
        car.setYearOfManufacturing(yearOfManufacturing);
        car.setNameOfCarInDB(nameOfCarInDB);
        carInDBRepository.save(car);

        model.addAttribute("title", "Factory Service Manuals");
        model.addAttribute("carSInDB", carInDBRepository.findAll());

        return "fsm/index";
    }

    @GetMapping(value="deleteCar/{idCar}")
    public String deleteCarInDBform(@PathVariable int idCar, Model model) {

        model.addAttribute("title", "Delete car form");
        model.addAttribute("carInDB", carInDBRepository.findById(idCar).get());

        return "fsm/carInDb/deleteCar";
    }

    @PostMapping(value = "deleteCar/{idCar}")
    public String processDeleteCarInDBform(@RequestParam int idCar, Model model) {

        carInDBRepository.deleteById(idCar);

        model.addAttribute("title", "Factory Service Manuals");
        model.addAttribute("carSInDB", carInDBRepository.findAll());

        return "fsm/index";

        //TODO: THINK ABOUT HOW TO DELETE LINES RELATED TO THIS CAR IN OTHER TABLES!!!!! MAYBE THAT STRANGE ATTRIBUTE ... OR MAYBE JUST LOCATED NEEDED AND DELETE THEM

    }





}
