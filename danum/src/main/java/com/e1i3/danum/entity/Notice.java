package com.e1i3.danum.entity;

import jakarta.persistence.*;

@Entity(name = "notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long noticeId;
    @Column
    String content;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
}
