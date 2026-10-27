package com.warehouse_backend.wh_backend.web.models;

import java.time.LocalDate;

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
public class Reservation {
    private Long reservationID;
    private Long vehicleID;
    private Long employeeID;
    private LocalDate expiryDate;
    private String status;
    private Long customerID;
}
