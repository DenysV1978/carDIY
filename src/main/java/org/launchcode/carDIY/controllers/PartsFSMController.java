package org.launchcode.carDIY.controllers;


import com.sun.xml.bind.v2.TODO;
import org.launchcode.carDIY.data.ManufacturersFSMRepository;
import org.launchcode.carDIY.data.PartsFSMRepository;
import org.launchcode.carDIY.models.ManufacturersFSM;
import org.launchcode.carDIY.models.PartsFSM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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
        model.addAttribute("manualID", manualID);
        model.addAttribute("images", manual.get().getImages());
        System.out.println("Stop");
        return "fsm/listOfManuals/manual";

    }

    //this method removes part from specific manual
    @GetMapping("removePart")
    public String removePartForm(@RequestParam int manualID, Model model) {
        model.addAttribute("title", "Removing parts from this manual: " + manufacturersFSMrepository.findById(manualID).get().getFsmName());
        model.addAttribute("manual", manufacturersFSMrepository.findById(manualID).get());
        model.addAttribute("partsList", manufacturersFSMrepository.findById(manualID).get().getPartsFSMList());

        return "fsm/listOfManuals/parts/removePart";

    }

    @PostMapping("removePart")
    public String processRemovePartForm(@RequestParam int manualID, String partID, Model model) {
        model.addAttribute("title", "Removing of parts from this manual: " + manufacturersFSMrepository.findById(manualID).get().getFsmName());

        String[] listOfParts  = partID.split(",");
        List<PartsFSM> partsFSMList1 =  new ArrayList<>();
        partsFSMList1 = manufacturersFSMrepository.findById(manualID).get().getPartsFSMList();


        ArrayList<Integer> listOfPartsIntegers = new ArrayList<>();
        for(int k = 0; k<listOfParts.length; k++) {
            Integer partInt = Integer.parseInt(listOfParts[k]);
            listOfPartsIntegers.add(partInt);
        }

        ManufacturersFSM manual1 =  manufacturersFSMrepository.findById(manualID).get();
        List<PartsFSM> listToRemove = new ArrayList<>();

        for(int i = 0; i<partsFSMList1.size(); i++) {
            Integer partFSMID = partsFSMList1.get(i).getId();
            if(listOfPartsIntegers.contains(partFSMID)) {

                listToRemove.add(partsFSMRepository.findById(partFSMID).get());

                }
            }
        manual1.getPartsFSMList().removeAll(listToRemove);
        manufacturersFSMrepository.save(manual1);

        model.addAttribute("title", "Factory Service Manual");
        Optional<ManufacturersFSM> manual = manufacturersFSMrepository.findById(manualID);
        model.addAttribute("manual", manual);
        model.addAttribute("images", manual.get().getImages());
        model.addAttribute("manualID", manualID);

        return "fsm/listOfManuals/manual";
    }

    @GetMapping("deleteFromDB/part")
    public String deletePartFromDBform(@RequestParam int partID, int manualID, Model model) {

        model.addAttribute("title", "Delete part from parts table in database");
        model.addAttribute("part", partsFSMRepository.findById(partID).get());
        model.addAttribute("manualID", manualID);

        return "fsm/listOfManuals/parts/deletePartFromDB";
    }

    @PostMapping("deleteFromDB/part")
    public String processDeletePartFromDBForm(@RequestParam int partID, int manualID, Model model) {

        Iterable<ManufacturersFSM> listOfManuals = manufacturersFSMrepository.findAll();
        for(ManufacturersFSM manual : listOfManuals) {
            manual.getPartsFSMList().remove(partsFSMRepository.findById(partID).get());
            manufacturersFSMrepository.save(manual);
        }

        partsFSMRepository.deleteById(partID);

        model.addAttribute("title", "Factory Service Manual");
        Optional<ManufacturersFSM> manual = manufacturersFSMrepository.findById(manualID);
        model.addAttribute("manual", manual);
        model.addAttribute("manualID", manualID);
        model.addAttribute("images", manual.get().getImages());


        return "fsm/listOfManuals/manual";
    }

//TODO: implement methods that will delete parts from PartsFSM... Think about that before deleting part from PartsFSM table, those parts should be deleted from join table...


}
