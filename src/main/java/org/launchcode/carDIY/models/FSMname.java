package org.launchcode.carDIY.models;

import javax.persistence.Entity;

@Entity
public class FSMname extends AbstractEntity {

    //this class is responsible for creation of standard names of jobs or services

    private String fsmName;

    //constructor

    public FSMname(String aFsmName) {
        this.fsmName = aFsmName;
    }

    public FSMname() {

    }

    //getters and setters

    public void setFsmName(String aFsmName) {
        this.fsmName = aFsmName;
    }

    public String getFsmName() {
        return fsmName;
    }

    @Override
    public String toString() {
        return fsmName;
    }
}
