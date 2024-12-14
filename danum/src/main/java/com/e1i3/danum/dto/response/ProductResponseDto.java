package com.e1i3.danum.dto.response;

import com.e1i3.danum.entity.Product;
import com.e1i3.danum.enumeration.ProductType;
import com.e1i3.danum.enumeration.TradeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductResponseDto {

    private Long id;
    private ProductType productType;
    private TradeType tradeType;
    private String productUrl;
    private String productName;
    private Long productPrice;
    private Long productCount;

    @Builder
    public ProductResponseDto(Product product) {
        this.id = product.getProductId();
        this.productType = product.getProductType();
        this.tradeType = product.getTradeType();
        this.productUrl = product.getProductUrl();
        this.productName = product.getProductName();
        this.productPrice = product.getPrice();
        this.productCount = product.getCount();
    }
}
