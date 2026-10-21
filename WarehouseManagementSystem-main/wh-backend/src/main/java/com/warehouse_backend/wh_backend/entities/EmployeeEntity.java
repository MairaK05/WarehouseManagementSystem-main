package com.warehouse_backend.wh_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="EMPLOYEES")
@Data
@ToString
public class EmployeeEntity {
    @Id
    @Column(name="EMPLOYEE_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long employeeID;

   @Column(name="FIRST_NAME")
   private String firstName;

   @Column(name="LAST_NAME") 
   private String lastName;

   @Column(name="EMAIL")
   private String email;

   @Column(name="PHONE_NUMBER")
   private String phoneNumber;

   @Column(name="ADDRESS")
   private String address;

   @Column(name="JOB_TITLE")
   private String jobTitle;
}