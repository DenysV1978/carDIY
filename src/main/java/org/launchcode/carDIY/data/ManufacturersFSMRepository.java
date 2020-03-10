package org.launchcode.carDIY.data;

import org.launchcode.carDIY.models.ManufacturersFSM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ManufacturersFSMRepository extends CrudRepository<ManufacturersFSM, Integer> {

    @Query(value = "SELECT * FROM MANUFACTURERSFSM WHERE car_indb_id = :carInDBID", nativeQuery = true)
    ArrayList<ManufacturersFSM> findAllbyCarInDBID(@Param("carInDBID") Integer carInDBID); //this is a query method

}
