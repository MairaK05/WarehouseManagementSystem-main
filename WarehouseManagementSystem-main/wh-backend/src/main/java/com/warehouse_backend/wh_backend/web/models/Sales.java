package com.warehouse_backend.wh_backend.web.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sales {
    private Long saleID;
    private Long employeeID;
    private Long vehicleID;
    private Long saleAmount;
    private Long customerID;
    private LocalDateTime saleTime;
}
