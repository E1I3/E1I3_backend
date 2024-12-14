package com.e1i3.danum.entity;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    @Column()
    String name;
    @Column()
    String email;
    @Column()
    Long valance;
}


