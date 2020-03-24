package org.launchcode.carDIY.controllers;

import org.launchcode.carDIY.data.ManufacturersFSMRepository;
import org.launchcode.carDIY.data.PartsFSMRepository;
import org.launchcode.carDIY.models.ManufacturersFSM;
import org.launchcode.carDIY.models.PartsFSM;
import org.launchcode.carDIY.models.dto.ManufacturersFSMPartsFSMDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("fsm/listOfManuals/manual")
public class PartsFSMController {

    @Autowired
    private PartsFSMRepository partsFSMRepository;

    @Autowired
    private ManufacturersFSMRepository manufacturersFSMrepository;



    @GetMapping("createPart")
    public String createNewPartForm(@RequestParam int manualID, Model model) {

        model.addAttribute("newPartFSM", new PartsFSM());
        model.addAttribute("title", "Creating new part");
        model.addAttribute("manualID", manualID);

        return "fsm/listOfManuals/parts/createNewPartFSM";
    }

    @PostMapping("createPart")
    public String processCreateNewPartForm(@ModelAttribute PartsFSM newPartFSM, @RequestParam int manualID, Model model) {
        model.addAttribute("title", "Create and assign new part");

        partsFSMRepository.save(newPartFSM);

        ManufacturersFSM manufacturersFSM = manufacturersFSMrepository.findById(manualID).get();
        manufacturersFSM.getPartsFSMList().add(newPartFSM);
        manufacturersFSMrepository.save(manufacturersFSM);

        model.addAttribute("title", "Factory Service Manual");
        Optional<ManufacturersFSM> manual = manufacturersFSMrepository.findById(manualID);
        model.addAttribute("manual", manual);
        System.out.println("Stop");
        return "fsm/listOfManuals/manual";

    }


}
