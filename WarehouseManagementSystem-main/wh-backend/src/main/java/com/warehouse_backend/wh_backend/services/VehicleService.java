package com.warehouse_backend.wh_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.warehouse_backend.wh_backend.entities.VehicleEntity;
import com.warehouse_backend.wh_backend.repositories.VehicleRepository;
import com.warehouse_backend.wh_backend.web.errors.NotFoundException;
import com.warehouse_backend.wh_backend.web.models.Vehicle;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    private VehicleEntity translateWebToDb(Vehicle vehicle) {
        VehicleEntity entity = new VehicleEntity();

        entity.setVehicleID(vehicle.getVehicleId());
        entity.setVehicleBrand(vehicle.getVehicleBrand());
        entity.setVehicleModel(vehicle.getVehicleModel());
        entity.setVehiclePrice(vehicle.getVehiclePrice());
        entity.setVehicleMileage(vehicle.getVehicleMileage());
        entity.setUsed(vehicle.isUsed());
        return entity;
    }

    public List<Vehicle> getAllVehicles(){
        Iterable<VehicleEntity> vehicleEntities = this.vehicleRepository.findAll();
        List<Vehicle> vehicles = new ArrayList<>();

        vehicleEntities.forEach(vehicleEntity -> {
            vehicles.add(translateDbToWeb(vehicleEntity));
        });

        return vehicles;
    }

    public Vehicle getVehicle(long id){
        Optional<VehicleEntity> optional = this.vehicleRepository.findById(id);
        if (optional.isEmpty()){
            throw new NotFoundException("NO VEHICLE WITH THIS ID");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Vehicle createOrUpdate(Vehicle vehicle){
        VehicleEntity entity = this.translateWebToDb(vehicle);
        entity = this.vehicleRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteVehicle(long id) {
        this.vehicleRepository.deleteById(id);
    }
    
    private Vehicle translateDbToWeb(VehicleEntity entity) {
        return new Vehicle(entity.getVehicleID(), entity.getVehicleModel(), entity.getVehicleBrand(), entity.getVehiclePrice(), entity.getVehicleMileage(), entity.isUsed());
    }
    

}
