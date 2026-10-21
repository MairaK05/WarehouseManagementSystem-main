package com.warehouse_backend.wh_backend.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse_backend.wh_backend.services.VehicleService;
import com.warehouse_backend.wh_backend.web.errors.BadRequestException;
import com.warehouse_backend.wh_backend.web.models.Vehicle;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vehicles")
public class VehicleRestController {

    private final VehicleService vehicleService;

    public VehicleRestController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getAll(){
        return this.vehicleService.getAllVehicles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle createEmployee(@RequestBody Vehicle vehicle){
        return this.vehicleService.createOrUpdate(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable("id")long id){
        return this.vehicleService.getVehicle(id);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable("id")long id, @RequestBody Vehicle vehicle){
        if(id != vehicle.getVehicleId()){
            throw new BadRequestException("MISMATCHED IDs");
        }
        return this.vehicleService.createOrUpdate(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable("id")long id){
        this.vehicleService.deleteVehicle(id);
    }

}
