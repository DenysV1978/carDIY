package org.launchcode.carDIY.models.dto;

import org.launchcode.carDIY.models.ManufacturersFSM;
import org.launchcode.carDIY.models.PartsFSM;

public class ManufacturersFSMPartsFSMDTO {

    private ManufacturersFSM manufacturersFSM;

    private PartsFSM partsFSM;

    public ManufacturersFSMPartsFSMDTO() {}

    //setters and getters


    public ManufacturersFSM getManufacturersFSM() {
        return manufacturersFSM;
    }

    public void setManufacturersFSM(ManufacturersFSM manufacturersFSM) {
        this.manufacturersFSM = manufacturersFSM;
    }

    public PartsFSM getPartsFSM() {
        return partsFSM;
    }

    public void setPartsFSM(PartsFSM partsFSM) {
        this.partsFSM = partsFSM;
    }
}
