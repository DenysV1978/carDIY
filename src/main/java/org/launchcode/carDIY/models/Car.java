package org.launchcode.carDIY.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Car extends AbstractEntity {

    //cars that belong to users

    private String nameOfTheCar;

    @ManyToOne
    private CarInDB carInDB;







}
