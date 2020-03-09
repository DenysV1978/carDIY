package org.launchcode.carDIY.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManufacturersFSM extends AbstractEntity {
    //this class includes different Factory Service Manuals for different cars

    @ManyToOne
    private CarInDB carInDB;

    @ManyToOne
    private FSMname fsmName;
    // TODO: need to check if you need to use OneToOne

    private String millageToRepeat;

    private String details;

    @OneToMany(mappedBy = "manufacturersFSM")
    private final List<PartsFSM> partsFSMList = new ArrayList<>();











}
