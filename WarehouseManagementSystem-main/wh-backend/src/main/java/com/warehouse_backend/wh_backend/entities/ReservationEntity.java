package com.warehouse_backend.wh_backend.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="RESERVATION")
@Data
@ToString
public class ReservationEntity {
    @Id
    @Column(name="reservation_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reservationID;
    @Column(name="vehicle_id")
    private Long vehicleID;
    @Column(name="employee_id")
    private Long employeeID;
    @Column(name="expiry_date")
    private LocalDate expiryDate;
    @Column(name="status")
    private String status;
    @Column(name="customer_id")
    private Long customerID;
}
