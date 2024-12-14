package com.e1i3.danum.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    @Column()
    String name;
    @Column()
    String email;
    @Column()
    Long balance;
    @Column()
    Long donateSum;
}


