package com.e1i3.danum.service;

import com.e1i3.danum.dto.request.CreateUserRequest;
import com.e1i3.danum.dto.response.CreateUserResponse;
import com.e1i3.danum.dto.response.MyPageSellerResponseDto;
import com.e1i3.danum.dto.response.MyPageUserResponseDto;
import com.e1i3.danum.entity.Store;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.repository.StoreRepository;
import com.e1i3.danum.repository.UserRepository;
import com.e1i3.danum.request.UpdateStatusToStoreRequest;
import com.e1i3.danum.response.UpdateStatusToStoreResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        User user = User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .balance(100000L)
                .build();

        User result = userRepository.save(user);

        return CreateUserResponse
                .builder()
                .userId(result.getUserId())
                .type(0L)
                .storeId(null)
                .build();
    }

    public UpdateStatusToStoreResponse updateStatusToStore(UpdateStatusToStoreRequest updateStatusToStoreRequest) throws Exception{
        // validate userId exists
        Long userId = userRepository.findById(updateStatusToStoreRequest.getUserId()).orElseThrow(NoSuchElementException::new).getUserId();

        // validate userId exists in store
        Store store = storeRepository.findByUserId(updateStatusToStoreRequest.getUserId());
        if (store == null)
            throw new NoSuchElementException();

        return UpdateStatusToStoreResponse.builder()
                .userId(updateStatusToStoreRequest.getUserId())
                .type(1L)
                .storeId(updateStatusToStoreRequest.getStoreId())
                .build();
    }

    // 판매자 마이페이지 조회
    @Transactional
    public MyPageSellerResponseDto viewSellerPage(Long storeId, Long userId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다. : " + storeId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. : " + userId));
        return new MyPageSellerResponseDto(store, user);
    }

    // 사용자 마이페이지 조회
    @Transactional
    public MyPageUserResponseDto viewUserPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. : " + userId));
        return new MyPageUserResponseDto(user);
    }



}
