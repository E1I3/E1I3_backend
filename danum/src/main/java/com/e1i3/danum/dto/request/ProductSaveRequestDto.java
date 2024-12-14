package com.e1i3.danum.dto.request;

import com.e1i3.danum.entity.Product;
import com.e1i3.danum.entity.Store;
import com.e1i3.danum.enumeration.ProductType;
import com.e1i3.danum.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ProductSaveRequestDto {

    private Long userId;
    private String name;
    private String productUrl;
    private Long count;
    private TradeType tradeType;
    private ProductType productType;

    public Product toEntity(Store store, String productUrl) {
        return Product.builder()
                .productName(this.name)
                .productUrl(this.productUrl)
                .count(this.count)
                .tradeType(this.tradeType)
                .productType(this.productType)
                .store(store)
                .build();
    }

}
