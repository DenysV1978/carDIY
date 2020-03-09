package org.launchcode.carDIY.data;


import org.launchcode.carDIY.models.CarInDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInDBRepository extends CrudRepository<CarInDB, Integer> {
}
