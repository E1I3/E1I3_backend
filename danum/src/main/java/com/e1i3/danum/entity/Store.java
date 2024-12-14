package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.StoreStatus;
import com.e1i3.danum.enumeration.StoreType;
import jakarta.persistence.*;

@Entity(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long storeId;
    @Column()
    String storeName;
    @Column()
    StoreType storeType;
    @Column()
    String address;
    @Column()
    Float latitude;
    @Column()
    Float longtitude;
    @Column()
    StoreStatus storeStatus;
    @Column()
    String store_url;
    @OneToOne(fetch = FetchType.LAZY)
    User user;
}
