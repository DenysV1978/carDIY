package org.launchcode.carDIY.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PartsFSM extends AbstractEntity {

    private String partName;

    private String partBrandName;

    private int cost;

    @ManyToOne
    private ManufacturersFSM manufacturersFSM;

    //constructor

    public PartsFSM() {}

    public PartsFSM(String partName, int cost, String partBrandName, ManufacturersFSM manufacturersFSM) {
        this.partName = partName;
        this.cost = cost;
        this.partBrandName = partBrandName;
        this.manufacturersFSM = manufacturersFSM;

    }

    //getters and setters

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setPartBrandName(String partBrandName) {
        this.partBrandName = partBrandName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPartName() {
        return partName;
    }

    public String getPartBrandName() {
        return partBrandName;
    }

    public int getCost() {
        return cost;
    }

    public ManufacturersFSM getManufacturersFSM() {
        return manufacturersFSM;
    }

    public void setManufacturersFSM(ManufacturersFSM manufacturersFSM) {
        this.manufacturersFSM = manufacturersFSM;
    }
}
