package com.e1i3.danum.controller;

import com.e1i3.danum.response.ReadStoreResponses;
import com.e1i3.danum.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class StoreController {
    private final StoreService storeService;
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


}
