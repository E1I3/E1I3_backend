package com.e1i3.danum.controller;

import com.e1i3.danum.dto.request.StoreRegisterRequestDto;
import com.e1i3.danum.request.UpdateTradeInfoRequest;
import com.e1i3.danum.response.ReadStoreResponses;
import com.e1i3.danum.s3.S3Uploader;
import com.e1i3.danum.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping()
@Tag(name = "가게 API", description = "가게 관련 기능 API입니다.")
public class StoreController {
    private final StoreService storeService;
    @Operation(summary = "위도 경도 + 필터 기반으로 가게 추출", description = "위도 경도 + 필터 기반으로 가게를 추출합니다")
    @GetMapping("/home/store")
    public ResponseEntity<ReadStoreResponses> readStore
            (@RequestParam(value = "location", required = false, defaultValue = "none") String location,
             @RequestParam(value = "name", required = false, defaultValue = "") String name,
             @RequestParam(value = "category", required = false, defaultValue = "none") String category){


        String[] position = location.split(",");

        Float latitude = Float.parseFloat(location.split(",")[0]);
        Float longitude = Float.parseFloat(location.split(",")[1]);

        String[] categories = category.split(",");
        ReadStoreResponses readStoreResponses = storeService.readStore(latitude, longitude, name, categories);

        return ResponseEntity.status(HttpStatus.OK).body(readStoreResponses);
    }



    // 판매자 등록 API
    @Operation(summary = "판매자 등록", description = "판매자를 등록합니다.")
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> registerSeller(
            @RequestPart("image") MultipartFile file, // 파일 처리
            @RequestPart("store") StoreRegisterRequestDto requestDto // JSON 처리
    ) throws IOException {
        storeService.registerSeller(file, requestDto);
        System.out.println("유저id" + requestDto.getUserId());
        return ResponseEntity.ok("판매자 등록이 완료되었습니다.");
    }


}
