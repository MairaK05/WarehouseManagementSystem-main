package com.warehouse_backend.wh_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="SALES")
@Data
@ToString
public class SalesEntity {
    @Id
    @Column(name="sale_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long saleID;    
    @Column(name="employee_id")
    Long employeeID;
    @Column(name="vehicle_id")
    Long vehicleID;
    @Column(name="sale_amount")
    Long saleAmount;
    @Column(name="customer_id")
    Long customerID;
    @Column(name="sale_time")
    LocalDateTime saleTime;
}
