package org.launchcode.carDIY.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManufacturersFSM extends AbstractEntity {
    //this class includes different Factory Service Manuals for different cars

    @ManyToOne
    private CarInDB carInDB;

    @OneToMany(mappedBy = "manufacturersFSM", cascade = {CascadeType.ALL })
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    private FSMname fsmName;
    // TODO: need to check if you need to use OneToOne

    private int millageToRepeat;

    private String details;

    @ManyToMany
    private final List<PartsFSM> partsFSMList = new ArrayList<>();

    //constructor
    public ManufacturersFSM() {

    };

    public ManufacturersFSM(CarInDB carInDB, FSMname fsmName, Integer millageToRepeat, String details) {
        this.carInDB = carInDB;
        this.fsmName = fsmName;
        this.millageToRepeat = millageToRepeat;
        this.details = details;
    };



    //setters and getters


    public CarInDB getCarInDB() {
        return carInDB;
    }

    public void setCarInDB(CarInDB carInDB) {
        this.carInDB = carInDB;
    }

    public FSMname getFsmName()  {
        return fsmName;
    }

    public void setFsmName(FSMname fsmName) {
        this.fsmName = fsmName;
    }

    public Integer getMillageToRepeat() {
        return millageToRepeat;
    }

    public void setMillageToRepeat(int millageToRepeat) {
        this.millageToRepeat = millageToRepeat;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<PartsFSM> getPartsFSMList() {
        return partsFSMList;
    }

    public List<Image> getImages() {
        return images;
    }




}
