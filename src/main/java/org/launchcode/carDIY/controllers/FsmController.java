package org.launchcode.carDIY.controllers;

import org.hibernate.SQLQuery;
import org.launchcode.carDIY.data.CarInDBRepository;
import org.launchcode.carDIY.data.FSMnameRepository;
import org.launchcode.carDIY.data.ManufacturersFSMRepository;
import org.launchcode.carDIY.data.PartsFSMRepository;
import org.launchcode.carDIY.models.CarInDB;
import org.launchcode.carDIY.models.FSMname;
import org.launchcode.carDIY.models.ManufacturersFSM;
import org.launchcode.carDIY.models.PartsFSM;
import org.launchcode.carDIY.models.dto.ManufacturersFSMPartsFSMDTO;
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

    @Autowired
    private PartsFSMRepository partsFSMRepository;


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
        CarInDB car = carInDBRepository.findById(carInDBID).get();
        model.addAttribute("car", car);
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
        model.addAttribute("manualID", manualID);
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
    public String processAddNewManualName(@ModelAttribute ManufacturersFSM newManufacturersFSM, @RequestParam int carInDBID, Model model) {

        manufacturersFSMRepository.save(newManufacturersFSM);

        ArrayList<ManufacturersFSM> listOfManuals = manufacturersFSMRepository.findAllbyCarInDBID(carInDBID);
        CarInDB car = carInDBRepository.findById(carInDBID).get();
        model.addAttribute("car", car);
        model.addAttribute("listOfManuals", listOfManuals);
        model.addAttribute("title", "List Of Factory Service Manuals available for this car");
        model.addAttribute("carInDBID", carInDBID);

        return "fsm/listOfManuals/listOfManuals";
    }

    @GetMapping("listOfManuals/manual/addPart")
    public String addPartToManual(@RequestParam int manualID, Model model) {

        model.addAttribute("title", "Adding of part to the FSM manual");
        model.addAttribute("partsFSMList", partsFSMRepository.findAll());

        Optional<ManufacturersFSM> result = manufacturersFSMRepository.findById(manualID);
        ManufacturersFSM manufacturersFSM = result.get();
        ManufacturersFSMPartsFSMDTO manufacturersFSMPartsFSM = new ManufacturersFSMPartsFSMDTO();
        manufacturersFSMPartsFSM.setManufacturersFSM(manufacturersFSM);
        model.addAttribute("manufacturersFSMPartsFSM", manufacturersFSMPartsFSM);
        //so, here we prepare empty DTO object and fill it with that part that is known now because we fill parts for this part
        // System.out.println("Stop");

        model.addAttribute("manualID", manualID);

        return "fsm/listOfManuals/addPart";
    }


    @PostMapping("listOfManuals/manual/addPart")
    public String processAddPartToManual(@ModelAttribute ManufacturersFSMPartsFSMDTO manufacturersFSMPartsFSM, @RequestParam int manualID, Model model) {


        ManufacturersFSM manufacturersFSM = manufacturersFSMPartsFSM.getManufacturersFSM();
        PartsFSM partsFSM = manufacturersFSMPartsFSM.getPartsFSM();
        manufacturersFSM.getPartsFSMList().add(partsFSM);
        manufacturersFSMRepository.save(manufacturersFSM);

        model.addAttribute("title", "Factory Service Manual");
        Optional<ManufacturersFSM> manual = manufacturersFSMRepository.findById(manualID);
        model.addAttribute("manual", manual);
        model.addAttribute("manualID", manualID);

        return "fsm/listOfManuals/manual";
        //TODO: maybe you want to write check loop to see if this part is already in this manual... or maybe not - maybe you want to allow having two "engine oils" as an option



    }

    @GetMapping("listOfManuals/manual/part")
    public String showPartDetails(@RequestParam int partID, int manualID, Model model) {

        PartsFSM part = partsFSMRepository.findById(partID).get();
        model.addAttribute("part", part);
        model.addAttribute("title", "Part:" + part.getPartName());
        model.addAttribute("manualID", manualID);
        model.addAttribute("manual", manufacturersFSMRepository.findById(manualID).get());

        return "fsm/listOfManuals/part";

    }

    @GetMapping("listOfManuals/editManual")
    public String editManualForm(@RequestParam int manualID, Model model) {

        model.addAttribute("title", "Edit manual");
        model.addAttribute("manual", manufacturersFSMRepository.findById(manualID).get());
        model.addAttribute("listOfFSMNames", fsmNameRepository.findAll());


        return "fsm/listOfManuals/editManual";
    }

    @PostMapping("listOfManuals/editManual")
    public String processEditManualForm(@RequestParam int manualID, int fsmName, int millageToRepeat, String details, Model model) {

        FSMname name = fsmNameRepository.findById(fsmName).get();
        ManufacturersFSM manualChanged = manufacturersFSMRepository.findById(manualID).get();
        manualChanged.setFsmName(name);
        manualChanged.setDetails(details);
        manualChanged.setMillageToRepeat(millageToRepeat);
        manufacturersFSMRepository.save(manualChanged);

        model.addAttribute("title", "Factory Service Manual");
        Optional<ManufacturersFSM> manual = manufacturersFSMRepository.findById(manualID);
        model.addAttribute("manual", manual);
        model.addAttribute("manualID", manualID);



        return "fsm/listOfManuals/manual";

    }

    @GetMapping("listOfManuals/deleteManual")
    public String deleteManualFrom(@RequestParam int manualID, Model model) {

        model.addAttribute("title", "Delete manual");
        model.addAttribute("manual", manufacturersFSMRepository.findById(manualID));
        model.addAttribute("manualID", manualID);

        return "fsm/listOfManuals/deleteManual";

    }

    @PostMapping("listOfManuals/deleteManual")
    public String processDeleteManualForm(@RequestParam int manualID, Model model) {

        int carInDBID = manufacturersFSMRepository.findById(manualID).get().getCarInDB().getId();

        manufacturersFSMRepository.deleteById(manualID);

        ArrayList<ManufacturersFSM> listOfManuals = manufacturersFSMRepository.findAllbyCarInDBID(carInDBID);
        CarInDB car = carInDBRepository.findById(carInDBID).get();
        model.addAttribute("car", car);
        model.addAttribute("listOfManuals", listOfManuals);
        model.addAttribute("title", "List Of Factory Service Manuals available for this car");
        model.addAttribute("carInDBID", carInDBID);

        return "fsm/listOfManuals/listOfManuals";




    }





}
