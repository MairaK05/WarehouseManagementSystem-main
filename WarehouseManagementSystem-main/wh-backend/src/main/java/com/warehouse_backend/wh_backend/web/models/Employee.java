package com.warehouse_backend.wh_backend.web.models;

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
public class Employee {
    public Long employeeID;
    public String firstName;
    public String lastName;
    public String emailAddress;
    public String phoneNumber;
    public String address;
    public String jobTitle;

}
