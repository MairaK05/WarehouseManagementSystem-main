package com.warehouse_backend.wh_backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.warehouse_backend.wh_backend.entities.ReservationEntity;
import com.warehouse_backend.wh_backend.repositories.ReservationRepository;
import com.warehouse_backend.wh_backend.web.models.Reservation;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationEntity translateWebToDb(Reservation reservation) {
        ReservationEntity entity = new ReservationEntity();
        entity.setReservationID(reservation.getReservationID());
        entity.setVehicleID(reservation.getVehicleID());
        entity.setExpiryDate(reservation.getExpiryDate());
        entity.setEmployeeID(reservation.getEmployeeID());
        entity.setStatus(reservation.getStatus());
        entity.setCustomerID(reservation.getCustomerID());
        return entity;
    }

    public Reservation translateDbToWeb(ReservationEntity reservationEntity) {
        return new Reservation(reservationEntity.getReservationID(), reservationEntity.getVehicleID(), reservationEntity.getEmployeeID(),
                reservationEntity.getExpiryDate(), reservationEntity.getStatus(), reservationEntity.getCustomerID());
    }

    public List<Reservation> getAllReservations() {
        Iterable<ReservationEntity> reservationEntities = this.reservationRepository.findAll();
        List<Reservation> reservations = new ArrayList<>();

        reservationEntities.forEach(reservationEntity -> {
            reservations.add(translateDbToWeb(reservationEntity));
        });

        return reservations;
    }

    public Reservation addOrUpdateReservation(Reservation newReservation) {
        ReservationEntity entity = translateWebToDb(newReservation);
        entity = reservationRepository.save(entity);
        return translateDbToWeb(entity);
    }

    public void endReservation(Long id) {
        this.reservationRepository.deleteById(id);
    }
}
