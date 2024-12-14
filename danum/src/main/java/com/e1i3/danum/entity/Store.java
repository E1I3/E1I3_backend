package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.StoreStatus;
import com.e1i3.danum.enumeration.StoreType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "stores")
@Getter
@Setter
@NoArgsConstructor
public class Store {

    @Builder
    public Store(Long storeId, String storeName, StoreType storeType, String address, Float latitude, Float longitude, StoreStatus storeStatus, String storeUrl, User user) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeType = storeType;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storeStatus = storeStatus;
        this.storeUrl = storeUrl;
        this.user = user;
    }

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
