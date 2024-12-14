package com.e1i3.danum.repository.custom;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.enumeration.StoreStatus;
import com.e1i3.danum.repository.StoreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Store> readStoreUsingFilter(Float latitude, Float longitude, String name, String[] categories) {
        StringBuilder sb = new StringBuilder();

        sb.append("select *\n")
                .append("from stores s\n")
                .append("where 1=1\n")
                .append("and store_name like \"%")
                .append(name)
                .append("%\"\n");
        //
        System.out.println(categories.length);
        System.out.println();

        if (!categories[0].equals("none"))
            for (String category : categories)
                sb.append("and store_status = ")
                        .append("\"")
                        .append(category)
                        .append("\"\n");
        //
        sb.append("and (6371 * 2 * ASIN(SQRT(POWER(SIN(RADIANS(s.latitude - ")
                .append(latitude) // latitude
                .append(")), 2)")
                .append("+ COS(RADIANS(")
                .append(latitude)
                .append(")) * COS(RADIANS(s.latitude))")
                .append("* POWER(SIN(RADIANS(s.longtitude - ")
                .append(longitude)
                .append(")), 2)))) <= ")
                .append(1)
                .append(";");

        List<Object []> results = em.createNativeQuery(sb.toString()).getResultList();

        List<Store> stores = new ArrayList<>();
        for (Object[] result : results) {
            Store store = mapToStore(result);
            stores.add(store);
        }

        return stores;
    }

    private Store mapToStore(Object[] result) {
        Store store = new Store();
        store.setStoreId(((Number) result[0]).longValue()); // ID 매핑
        store.setStoreName((String) result[4]); // storeName 매핑
        store.setAddress((String) result[1]); // address 매핑
        store.setLatitude(((Number) result[2]).floatValue()); // latitude 매핑
        store.setLongitude(((Number) result[3]).floatValue()); // longitude 매핑
        store.setStoreStatus(StoreStatus.valueOf(String.valueOf(result[5]))); // Float를 String으로 변환 후 매핑
        store.setStoreUrl((String) result[7]); // URL 매핑
        return store;
    }
}
