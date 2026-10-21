package com.warehouse_backend.wh_backend.repositories;

import com.warehouse_backend.wh_backend.entities.VehicleEntity;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<VehicleEntity, Long> {


}
