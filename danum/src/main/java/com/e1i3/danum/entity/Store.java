package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.StoreStatus;
import com.e1i3.danum.enumeration.StoreType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "stores")
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long storeId;
    @Column()
    String storeName;
    @Column()
    @Enumerated(EnumType.STRING)
    StoreType storeType;
    @Column()
    String address;
    @Column()
    Float latitude;
    @Column()
    Float longitude;
    @Column()
    @Enumerated(EnumType.STRING)
    StoreStatus storeStatus;
    @Column()
    String storeUrl;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    User user;
}
