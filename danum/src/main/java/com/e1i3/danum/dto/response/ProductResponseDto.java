package com.e1i3.danum.dto.request;

import com.e1i3.danum.enumeration.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductSaveRequestDto {

    private Long id;
    ProductType productType;

}
