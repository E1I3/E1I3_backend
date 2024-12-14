package com.e1i3.danum.service;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.repository.StoreRepository;
import com.e1i3.danum.response.ReadStoreResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    public ReadStoreResponses readStore(Float latitude, Float longitude, String name, String[] categories){
        List<Store> stores = storeRepository.readStoreUsingFilter(latitude, longitude, name, categories);

        return ReadStoreResponses.toDto(stores);
    }
}
