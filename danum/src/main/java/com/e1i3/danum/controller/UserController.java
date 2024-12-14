package com.e1i3.danum.controller;

import com.e1i3.danum.dto.request.CreateUserRequest;
import com.e1i3.danum.dto.response.CreateUserResponse;
import com.e1i3.danum.request.UpdateStatusToStoreRequest;
import com.e1i3.danum.response.ReadStoreResponses;
import com.e1i3.danum.response.UpdateStatusToStoreResponse;
import com.e1i3.danum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {
    private final UserService userService;
    @PostMapping("/user/sign")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest){
        CreateUserResponse createUserResponse = userService.createUser(createUserRequest);

        return ResponseEntity.status(HttpStatus.OK).body(createUserResponse);
    }
    @PostMapping("/user/status-store")
    public ResponseEntity<UpdateStatusToStoreResponse> updateStatusToStore(@RequestBody UpdateStatusToStoreRequest updateStatusToStoreRequest) throws Exception {
        UpdateStatusToStoreResponse updateStatusToStoreResponse = userService.updateStatusToStore(updateStatusToStoreRequest);

        return ResponseEntity.status(HttpStatus.OK).body(updateStatusToStoreResponse);
    }


}
