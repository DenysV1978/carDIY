package org.launchcode.carDIY.controllers;

import org.hibernate.SQLQuery;
import org.launchcode.carDIY.data.CarInDBRepository;
import org.launchcode.carDIY.data.FSMnameRepository;
import org.launchcode.carDIY.data.ManufacturersFSMRepository;
import org.launchcode.carDIY.models.FSMname;
import org.launchcode.carDIY.models.ManufacturersFSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("fsm")
public class FsmController {

    @Autowired
    private CarInDBRepository carInDBRepository;

    @Autowired
    private ManufacturersFSMRepository manufacturersFSMRepository;

    @Autowired
    private FSMnameRepository fsmNameRepository;


    @GetMapping()
    public String chooseFSMbranch(Model model) {
        model.addAttribute("title", "Factory Service Manuals");
        model.addAttribute("carSInDB", carInDBRepository.findAll());

        return "fsm/index";

    }

    //TODO: implement search by factory, model, name, whatever...

    @GetMapping("listOfManuals")
    public String showListOfManuals(@RequestParam Integer carInDBID, Model model) {
//this is the first way to do it. Using simple Java coding manipulations
//        Iterable<ManufacturersFSM> listOfManufacturersFSM = manufacturersFSMRepository.findAll();
////        ArrayList<ManufacturersFSM> listOfManuals = new ArrayList<>();
////
////        for(ManufacturersFSM t : listOfManufacturersFSM) {
////            if(t.getCarInDB().getId() == carInDBID) {
////                listOfManuals.add(t);
////            }
////        }

        //this is the second way to do it - using query method implemented in ManufacturersFSMRepository
        ArrayList<ManufacturersFSM> listOfManuals = manufacturersFSMRepository.findAllbyCarInDBID(carInDBID);
        model.addAttribute("listOfManuals", listOfManuals);
        model.addAttribute("title", "List Of Factory Service Manuals available for this car");
        model.addAttribute("carInDBID", carInDBID);

        return "fsm/listOfManuals/listOfManuals";


    }

    @GetMapping("listOfManuals/manual")
    public String showChosenManual(@RequestParam int manualID, Model model) {
        model.addAttribute("title", "Factory Service Manual");
        Optional<ManufacturersFSM> manual = manufacturersFSMRepository.findById(manualID);
        model.addAttribute("manual", manual);
        System.out.println("Stop");
        return "fsm/listOfManuals/manual";
    }

    @GetMapping("listOfManuals/addNewManual")
    public String addNewManualName(@RequestParam int carInDBID, Model model) {

        model.addAttribute("Title", "Add new Factory Standard Manual");

        //list of FSM names available
        Iterable<FSMname> listOfFSMNames = fsmNameRepository.findAll();
        model.addAttribute("listOfFSMNames", listOfFSMNames);

        //need it for model binding

        model.addAttribute("newManufacturersFSM", new ManufacturersFSM());

        model.addAttribute("carInDBID", carInDBID);

        //System.out.println("Stop");

        return "fsm/listOfManuals/add";

    }

    @PostMapping("listOfManuals/addNewManual")
    public String processAddNewManualName(@ModelAttribute ManufacturersFSM newManufacturersFSM, Model model) {


        //List<ManufacturersFSM> result = carInDBRepository.findById(carInDBID).get().getManufacturersFSM();
        manufacturersFSMRepository.save(newManufacturersFSM);
        //List<ManufacturersFSM> result = manufacturersFSMRepository.findAllbyCarInDBID(carInDBID);
        //System.out.println("Stop");


        return "index";

    }




}
