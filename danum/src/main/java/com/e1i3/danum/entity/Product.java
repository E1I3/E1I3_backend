package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.ProductType;
import com.e1i3.danum.enumeration.TradeType;
import jakarta.persistence.*;

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
    @ManyToOne
    Store store;
}
