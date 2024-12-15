package com.e1i3.danum.service;

import com.e1i3.danum.dto.request.ProductSaveRequestDto;
import com.e1i3.danum.dto.response.ProductResponseDto;
import com.e1i3.danum.entity.Product;
import com.e1i3.danum.entity.Store;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.enumeration.TradeType;
import com.e1i3.danum.repository.ProductRepository;
import com.e1i3.danum.repository.StoreRepository;
import com.e1i3.danum.repository.UserRepository;
import com.e1i3.danum.response.ReadProductResponse;
import com.e1i3.danum.response.ReadStoreAndProductResponse;
import com.e1i3.danum.s3.S3Uploader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final S3Uploader s3Uploader;
    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;


    // 거래중인 상품 반환
    @Transactional
    public ReadStoreAndProductResponse viewProductsById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 storeId입니다."));

        List<Product> products = productRepository.findByStoreId(store.getStoreId());
        products = products.stream().filter(it -> it.getCount() > 0 && it.getTradeType() == TradeType.TRADE).collect(Collectors.toList());

        List<ReadProductResponse> readProductResponses = new ArrayList<>();

        for (Product product: products){
            readProductResponses.add(ReadProductResponse.builder()
                            .productId(product.getProductId())
                            .productName(product.getProductName())
                            .productUrl(product.getProductUrl())
                            .productPrice(product.getPrice())
                            .productCount(product.getCount())
                    .build());
        }

        return ReadStoreAndProductResponse.builder()
                .storeId(store.getStoreId())
        .storeUrl(store.getStoreUrl())
        .storeAddress(store.getAddress())
        .storeName(store.getStoreName())
                .products(readProductResponses)
                .build();
    }

    // 나눔중인 상품 반환
    @Transactional
    public ReadStoreAndProductResponse viewShareProductsById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 storeId입니다."));

        List<Product> products = productRepository.findByStoreId(store.getStoreId());
        products = products.stream().filter(it -> it.getCount() > 0 && it.getTradeType() == TradeType.SHARE).collect(Collectors.toList());

        List<ReadProductResponse> readProductResponses = new ArrayList<>();

        for (Product product: products){
            readProductResponses.add(ReadProductResponse.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .productUrl(product.getProductUrl())
                    .productPrice(product.getPrice())
                    .productCount(product.getCount())
                    .build());
        }

        return ReadStoreAndProductResponse.builder()
                .storeId(store.getStoreId())
                .storeUrl(store.getStoreUrl())
                .storeAddress(store.getAddress())
                .storeName(store.getStoreName())
                .products(readProductResponses)
                .build();
    }


    // 상품 등록
    @Transactional
    public ProductResponseDto saveProduct(MultipartFile file, ProductSaveRequestDto requestDto) throws IOException, IOException {
        String storedFileName = s3Uploader.upload(file, "product-images");
        requestDto.setProductUrl(storedFileName);
        Store store = storeRepository.findByUser(isUser(requestDto.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("Store not found for user id"));
        Product product = requestDto.toEntity(store, storedFileName);
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDto(savedProduct);
    }

    // 판매자 필터
    @Transactional
    public Store isSeller(Long userId) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Store store = storeRepository.findByUser(currentUser)
                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
        return store;
    }

    // 사용자 필터
    @Transactional
    public User isUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }




}
