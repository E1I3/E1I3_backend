package com.e1i3.danum.entity;

import com.e1i3.danum.enumeration.ProductType;
import com.e1i3.danum.enumeration.TradeType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Getter
@NoArgsConstructor
public class Product {

    @Builder
    public Product(Long productId, String productName, ProductType productType, Long count, Long price, String description, String productUrl, TradeType tradeType, Store store) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.count = count;
        this.price = price;
        this.description = description;
        this.productUrl = productUrl;
        this.tradeType = tradeType;
        this.store = store;
    }

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
