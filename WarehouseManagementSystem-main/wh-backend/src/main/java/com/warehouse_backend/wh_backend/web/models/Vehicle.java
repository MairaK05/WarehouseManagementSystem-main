package com.warehouse_backend.wh_backend.web.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Vehicle {
    public Long vehicleId;
    public String vehicleModel;
    public String vehicleBrand;
    public BigDecimal vehiclePrice;
    public Long vehicleMileage;
    public boolean isUsed;

}
