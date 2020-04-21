package org.launchcode.carDIY.data;

import org.launchcode.carDIY.models.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
