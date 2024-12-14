package com.e1i3.danum.repository;

import com.e1i3.danum.entity.Reservation;
import com.e1i3.danum.repository.custom.ReservationRepositoryCustom;
import com.e1i3.danum.response.ReadReservationByStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom {

}
