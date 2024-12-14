package com.e1i3.danum.controller;

import com.e1i3.danum.dto.request.CreateUserRequest;
import com.e1i3.danum.dto.response.CreateUserResponse;
import com.e1i3.danum.dto.response.MyPageSellerResponseDto;
import com.e1i3.danum.dto.response.MyPageUserResponseDto;
import com.e1i3.danum.request.UpdateStatusToStoreRequest;
import com.e1i3.danum.response.ReadStoreResponses;
import com.e1i3.danum.response.UpdateStatusToStoreResponse;
import com.e1i3.danum.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "판매자 마이페이지 조회",
            description = "storeId와 userId에 대한 판매자 마이페이지 정보를 조회합니다.")
    @GetMapping("/account/store/{storeId}/{userId}")
    public ResponseEntity<MyPageSellerResponseDto> viewSellerPage(
            @PathVariable Long storeId,
            @PathVariable Long userId) {

        MyPageSellerResponseDto responseDto = userService.viewSellerPage(storeId, userId);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "사용자 마이페이지 조회",
            description = "userId에 대한 사용자 마이페이지 정보를 조회합니다.")
    @GetMapping("/account/user/{userId}")
    public ResponseEntity<MyPageUserResponseDto> viewUserPage(
            @PathVariable Long userId) {
        MyPageUserResponseDto responseDto = userService.viewUserPage(userId);
        return ResponseEntity.ok(responseDto);
    }



}
