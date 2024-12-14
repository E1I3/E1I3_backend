package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.ResvType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resvId;
    @Column()
    LocalDateTime resvTime;
    @Column()
    ResvType resvType;
    @Column()
    Long count;
}
