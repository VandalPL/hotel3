package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    void saveReservation(Reservation reservation);
    Page<Reservation> getAllReservations(Pageable pageable);
    void deleteReservation(long id);
    Reservation getReservation(long id);
}
