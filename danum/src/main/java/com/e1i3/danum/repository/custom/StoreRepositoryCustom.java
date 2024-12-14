package com.e1i3.danum.repository.custom;

import com.e1i3.danum.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> readStoreUsingFilter(Float latitude, Float longitude, String name, String[] categories);
}
