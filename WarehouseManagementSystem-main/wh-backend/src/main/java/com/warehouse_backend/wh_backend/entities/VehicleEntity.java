package com.warehouse_backend.wh_backend.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="VEHICLES")
@Data
@ToString
public class VehicleEntity {
    @Id
    @Column(name = "VEHICLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleID;

    @Column(name = "VEHICLE_MODEL")
    private String vehicleModel;

    @Column(name = "VEHICLE_BRAND")
    private String vehicleBrand;

    @Column(name = "VEHICLE_PRICE")
    private BigDecimal vehiclePrice;

    @Column(name = "VEHICLE_MILEAGE")
    private Long vehicleMileage;

    @Column(name = "VEHICLE_IS_USED")
    private boolean isUsed;
}
