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
public class Customer {
    private Long customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate createdAt;
}
