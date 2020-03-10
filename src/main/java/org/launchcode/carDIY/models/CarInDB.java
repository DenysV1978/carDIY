package org.launchcode.carDIY.models;

import com.sun.xml.bind.v2.TODO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarInDB extends AbstractEntity {

    private String nameOfCarInDB;

    private String nameOfManufacturer;

    private int yearOfManufacturing;

    private String modelOfCar;

    @OneToMany(mappedBy = "carInDB")
    private List<ManufacturersFSM> manufacturersFSM = new ArrayList<>();
    //TODO: make sure to include check if the same FSMname is not repeated multiple times. One car - one FSMname - one FSM

    //constructor

    public CarInDB() {}

    public CarInDB(String nameOfCarInDB, String nameOfManufacturer, int yearOfManufacturing, String modelOfCar) {

                this.nameOfManufacturer = nameOfManufacturer;
                this.yearOfManufacturing = yearOfManufacturing;
                this.modelOfCar = modelOfCar;
                this.nameOfCarInDB = nameOfCarInDB;

        }

        //setters and getters


        public String getNameOfCarInDB() {
                return nameOfCarInDB;
        }

        public void setNameOfCarInDB(String aNameOfCarInDB) {
                this.nameOfCarInDB = aNameOfCarInDB;;
        }

        public String getNameOfManufacturer() {
                return nameOfManufacturer;
        }

        public void setNameOfManufacturer(String aNameOfManufacturer) {
                this.nameOfManufacturer = aNameOfManufacturer;
        }

        public int getYearOfManufacturing() {
                return yearOfManufacturing;
        }

        public void setYearOfManufacturing(int aYearOfManufacturing) {
                this.yearOfManufacturing = aYearOfManufacturing;
        }

        public String getModelOfCar() {
                return modelOfCar;
        }


        public void setModelOfCar(String modelOfCar) {
                this.modelOfCar = modelOfCar;
        }

        public List<ManufacturersFSM> getManufacturersFSM() {
            return manufacturersFSM;
        }
}
