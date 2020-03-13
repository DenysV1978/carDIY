package org.launchcode.carDIY.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PartsFSM extends AbstractEntity {

    private String partName;

    private String partBrandName;

    private int cost;

    @ManyToMany(mappedBy = "partsFSMList")
    private final List<ManufacturersFSM> manufacturersFSMList = new ArrayList<>();

    //constructor

    public PartsFSM() {}

    public PartsFSM(String partName, int cost, String partBrandName) {
        this.partName = partName;
        this.cost = cost;
        this.partBrandName = partBrandName;


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

    public List<ManufacturersFSM> getManufacturersFSMList() {
        return manufacturersFSMList;
    }
}
