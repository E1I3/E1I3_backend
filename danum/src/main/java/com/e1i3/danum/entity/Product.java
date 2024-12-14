package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.ProductType;
import com.e1i3.danum.enumeration.TradeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;
    @Column()
    String productName;
    @Column()
    ProductType productType;
    @Column()
    Long count;
    @Column()
    Long price;
    @Column()
    String description;
    @Column()
    String productUrl;
    @Column()
    TradeType tradeType;
    @JoinColumn(name = "store_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Store store;
}
