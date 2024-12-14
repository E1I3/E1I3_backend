package com.e1i3.danum.controller;

import com.e1i3.danum.dto.request.ProductSaveRequestDto;
import com.e1i3.danum.dto.response.ProductResponseDto;
import com.e1i3.danum.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/home/product")
@RequiredArgsConstructor
@Tag(name = "상품 API", description = "상품 관련 기능 API입니다.")
public class ProductController {

    private final ProductService productService;

    // 판매자 상품 조회
    @Operation(summary = "가게 거래 상품 조회", description = "가게 ID로 상품을 조회합니다.")
    @GetMapping("/view/trade/{storeId}")
    public ResponseEntity<List<ProductResponseDto>> viewProducts(@PathVariable Long storeId){
        List<ProductResponseDto> productList = productService.viewProductsById(storeId);
        return ResponseEntity.ok(productList);
    }

    // 판매자 상품 조회
    @Operation(summary = "가게 나눔 상품 조회", description = "가게 ID로 상품을 조회합니다.")
    @GetMapping("/view/share/{storeId}")
    public ResponseEntity<List<ProductResponseDto>> viewShareProducts(@PathVariable Long storeId){
        List<ProductResponseDto> productList = productService.viewShareProductsById(storeId);
        return ResponseEntity.ok(productList);
    }

    // 판매자 상품 등록
    @Operation(summary = "판매자 상품 등록", description = "상품을 등록합니다.")
    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public ResponseEntity<ProductResponseDto> saveProduct(
            @RequestPart("image") MultipartFile image,
            @ModelAttribute ProductSaveRequestDto requestDto
    ) throws IOException {
        ProductResponseDto responseDto = productService.saveProduct(image, requestDto);
        return ResponseEntity.ok(responseDto);
    }


}