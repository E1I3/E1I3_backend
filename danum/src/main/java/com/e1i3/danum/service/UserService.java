package com.e1i3.danum.service;

import com.e1i3.danum.dto.request.CreateUserRequest;
import com.e1i3.danum.dto.response.CreateUserResponse;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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
}
