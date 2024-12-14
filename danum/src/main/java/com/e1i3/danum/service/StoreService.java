package com.e1i3.danum.service;

import com.e1i3.danum.dto.request.StoreRegisterRequestDto;
import com.e1i3.danum.entity.Product;
import com.e1i3.danum.entity.Reservation;
import com.e1i3.danum.entity.Store;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.repository.ProductRepository;
import com.e1i3.danum.repository.StoreRepository;
import com.e1i3.danum.repository.UserRepository;
import com.e1i3.danum.request.UpdateTradeInfoRequest;
import com.e1i3.danum.response.ReadStoreResponses;
import com.e1i3.danum.s3.S3Uploader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    private final S3Uploader s3Uploader;

    private final UserRepository userRepository;
    public ReadStoreResponses readStore(Float latitude, Float longitude, String name, String[] categories){
        List<Store> stores = storeRepository.readStoreUsingFilter(latitude, longitude, name, categories);

        return ReadStoreResponses.toDto(stores);
    }

    // 판매자 등록 API
    @Transactional
    public void registerSeller(MultipartFile file, StoreRegisterRequestDto requestDto) throws IOException {
        String storedFileName = s3Uploader.upload(file,"store-images");
        requestDto.setStoreUrl(storedFileName);
        System.out.println(1);
        User currentUser = isUser(requestDto.getUserId());
        System.out.println(2);
        Store newStore = requestDto.toEntity(currentUser);
        log.info(requestDto.getUserId().toString());
        System.out.println(3);
        Store savedStore = storeRepository.save(newStore);
        System.out.println(4);
    }


    // 사용자 필터
    public User isUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}


