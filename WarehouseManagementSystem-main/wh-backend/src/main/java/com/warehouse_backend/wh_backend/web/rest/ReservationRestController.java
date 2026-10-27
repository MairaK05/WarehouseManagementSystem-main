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

import com.warehouse_backend.wh_backend.web.errors.BadRequestException;
import com.warehouse_backend.wh_backend.web.models.Reservation;


import com.warehouse_backend.wh_backend.services.ReservationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations(){
        return this.reservationService.getAllReservations();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody Reservation reservation){
        return this.reservationService.addOrUpdateReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id")long id, @RequestBody Reservation reservation){
        if (id != reservation.getReservationID()){
            throw new BadRequestException("MISMATCHED ID");
        }
        return this.reservationService.addOrUpdateReservation(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id")long id, @RequestBody Reservation reservation){
        if (id != reservation.getReservationID()){
            throw new BadRequestException("MISMATCHED ID");
        }
        this.reservationService.endReservation(id);
    }
}
