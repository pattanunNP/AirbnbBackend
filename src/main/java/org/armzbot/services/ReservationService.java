package org.armzbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.entity.Reservation;
import org.armzbot.repository.ReservationRepository;
import org.armzbot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;


    public List<Reservation> getAllReservationsByUserId(String user_id) throws IOException {
        if (user_id == null) {
            throw new IOException("user_id is null");
        }
        if (!userRepository.existsById(user_id)) {
            throw new IOException("Not found user_id : " + user_id);
        }
        return reservationRepository.findByUserId(user_id);
    }

    public Reservation getReservationById(String reserve_id) throws IOException {
        Optional<Reservation> repository = reservationRepository.findByBookingId(reserve_id);
        if (repository.isEmpty()) {
            throw new IOException("Not found reservation with id : " + reserve_id);
        }
        return repository.get();
    }

    public void addReservation(String user_id, Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(String reserve_id, Reservation reservation) throws IOException {
        Optional<Reservation> repository = reservationRepository.findByBookingId(reserve_id);
        if (repository.isEmpty()) {
            throw new IOException("Not found reservation with id : " + reserve_id);
        }
        Reservation result = repository.get();
        reservationRepository.save(result);
    }

    public void deleteReservationById(String reserve_id) throws IOException {
        Optional<Reservation> repository = reservationRepository.findByBookingId(reserve_id);
        if (repository.isEmpty()) {
            throw new IOException("Not found reservation with id : " + reserve_id);
        }
        Reservation deleteReserve = repository.get();
        deleteReserve.setIsActive(false);
        reservationRepository.save(deleteReserve);
    }

    public void deleteAllReservationsOfUser(String user_id) {

        List<Reservation> repositories = reservationRepository.findByUserId(user_id);
        for (Reservation deleteReserve : repositories) {
            deleteReserve.setIsActive(false);
            reservationRepository.save(deleteReserve);
        }
    }
}
