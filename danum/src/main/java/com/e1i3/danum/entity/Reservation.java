package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.ResvType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resvId;
    @Column()
    LocalDateTime resvTime;
    @Column()
    @Enumerated(EnumType.STRING)
    ResvType resvType;
    @Column()
    Long count;
    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;
}
