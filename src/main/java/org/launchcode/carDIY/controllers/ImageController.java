package org.launchcode.carDIY.controllers;


import com.mysql.cj.jdbc.Blob;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.launchcode.carDIY.data.ImageRepository;
import org.launchcode.carDIY.data.ManufacturersFSMRepository;
import org.launchcode.carDIY.models.Image;
import org.launchcode.carDIY.models.ManufacturersFSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;

@Controller
@RequestMapping("fsm/listOfManuals/manual")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ManufacturersFSMRepository manufacturersFSMRepository;


    @GetMapping("addImage")
    public String addImageToManual(@RequestParam int manualID, Model model) {


        model.addAttribute("title", "Add new image to the manual "+ manufacturersFSMRepository.findById(manualID).get().getFsmName() + " for the car: " + manufacturersFSMRepository.findById(manualID).get().getCarInDB().getNameOfCarInDB() + ".");
        model.addAttribute("manualID", manualID);


        return "fsm/listOfManuals/images/addImageForm";
    }

    @PostMapping("addImage")
    public String processAddImageToManual(@RequestParam("file") MultipartFile file, String name, String description, String filename, int manualID, Model model) throws IOException {

            byte[] arr = file.getBytes();
            String type = file.getContentType();


            Image image = new Image();
            image.setName(name);
            image.setDescription(description);
            image.setFilename(filename);
            image.setContent(arr);
            image.setManufacturersFSM(manufacturersFSMRepository.findById(manualID).get());
            imageRepository.save(image);


            return "redirect:?manualID=" + manualID;
    }


    @GetMapping("image")
    public String showImage(@RequestParam int imageID, int manualID, Model model) {

         model.addAttribute("image", imageRepository.findById(imageID).get().returnBase64());
         model.addAttribute("imageID", imageID);
         model.addAttribute("manualID", manualID);
         model.addAttribute("title", "Image. Name: " + imageRepository.findById(imageID).get().getName() + ". Description of the image: " + imageRepository.findById(imageID).get().getDescription() + ".");

         return "fsm/listOfManuals/images/index.html";

    }

    @GetMapping("removeImage")
    public String removeImageFromDB(@RequestParam int manualID, int imageID, Model model) {

        model.addAttribute("title", "Remove images from the manual: " + manufacturersFSMRepository.findById(manualID).get().getFsmName() + ". For the car: " + manufacturersFSMRepository.findById(manualID).get().getCarInDB().getNameOfCarInDB() + ".");
        //model.addAttribute("images", manufacturersFSMRepository.findById(manualID).get().getImages());
        model.addAttribute("image", imageRepository.findById(imageID).get().returnBase64());
        model.addAttribute("manualID", manualID);

        return "fsm/listOfManuals/images/removeImageFromDB";

    }

    @PostMapping("removeImage")
        public String removeImageFromDBProcessing(@RequestParam int imageID, int manualID, Model model) {

        imageRepository.deleteById(imageID);

        return "redirect:?manualID=" + manualID;

    }








}
