package org.launchcode.carDIY.controllers;

import org.launchcode.carDIY.data.CarInDBRepository;
import org.launchcode.carDIY.data.FSMnameRepository;
import org.launchcode.carDIY.data.ManufacturersFSMRepository;
import org.launchcode.carDIY.data.PartsFSMRepository;
import org.launchcode.carDIY.models.CarInDB;
import org.launchcode.carDIY.models.FSMname;
import org.launchcode.carDIY.models.ManufacturersFSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("fsm")
public class FSMNameController {

    @Autowired
    private FSMnameRepository fsmNameRepository;

    @Autowired
    private ManufacturersFSMRepository manufacturersFSMRepository;

    @Autowired
    private CarInDBRepository carInDBRepository;

    @GetMapping("addFSMName")
    public String addFSMNameForm(@RequestParam int carInDBID, Model model) {

        model.addAttribute("title", "Creation on the name of Factory Service Manual");
        model.addAttribute("newFSMName", new FSMname());
        model.addAttribute("carInDBID", carInDBID);

        return "fsm/FSMName/addFSMName";
    }

    @PostMapping("addFSMName")
    public String processAddFSMNameForm(@ModelAttribute FSMname newFSMName, @RequestParam int carInDBID, Model model) {

        fsmNameRepository.save(newFSMName);

        return "redirect:listOfManuals/addNewManual?carInDBID="+carInDBID;

    }
}
