package org.launchcode.carDIY.data;

import org.launchcode.carDIY.models.ManufacturersFSM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturersFSMRepository extends CrudRepository<ManufacturersFSM, Integer> {

}
