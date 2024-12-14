package com.e1i3.danum.repository.custom;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.enumeration.ResvType;
import com.e1i3.danum.response.ReadReservationByStoreResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom{
    @PersistenceContext
    EntityManager em;

    @Override
    public List<ReadReservationByStoreResponse> findReservationByStoreId(Long storeId) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT r.resv_id, r.resv_time, r.resv_type, ")
                .append("p.product_id, p.product_name, p.product_url, p.price, p.count ")
                .append("FROM reservations r ")
                .append("JOIN products p ON r.product_id = p.product_id ")
                .append("JOIN stores s ON s.store_id = p.store_id ")
                .append("WHERE 1=1 ")
                .append("AND s.store_id = :storeId ");

        List<Object []> results = em.createNativeQuery(sb.toString())
                .setParameter("storeId", storeId)
                .getResultList();

        List<ReadReservationByStoreResponse> rests = new ArrayList<>();
        for (Object[] result : results) {
            ReadReservationByStoreResponse readReservationByStoreResponse = mapToReadReservationByStoreResponse(result);
            rests.add(readReservationByStoreResponse);
        }

        return rests;
    }

    @Override
    public List<ReadReservationByStoreResponse> findReservationByUserId(Long userId) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT r.resv_id, r.resv_time, r.resv_type, ")
                .append("p.product_id, p.product_name, p.product_url, p.price, p.count ")
                .append("FROM reservations r ")
                .append("JOIN products p ON r.product_id = p.product_id ")
                .append("JOIN users u ON r.user_id = u.user_id ")
                .append("WHERE 1=1 ")
                .append("AND u.user_id = :userId ");

        List<Object []> results = em.createNativeQuery(sb.toString())
                .setParameter("userId", userId)
                .getResultList();

        List<ReadReservationByStoreResponse> rests = new ArrayList<>();
        for (Object[] result : results) {
            ReadReservationByStoreResponse readReservationByStoreResponse = mapToReadReservationByStoreResponse(result);
            rests.add(readReservationByStoreResponse);
        }

        return rests;
    }

    private ReadReservationByStoreResponse mapToReadReservationByStoreResponse(Object[] result) {
        return ReadReservationByStoreResponse.builder()
                .reserveId(((Number) result[0]).longValue()) // resv_id
                .resvTime(((java.sql.Timestamp) result[1]).toLocalDateTime()) // resv_time
                .resvType(ResvType.valueOf((String) result[2])) // resv_type
                .productId(((Number) result[3]).longValue()) // product_id
                .productName((String) result[4]) // product_name
                .productUrl((String) result[5])
                .price(((Number) result[6]).longValue()) // price
                .count(((Number) result[7]).intValue()) // count
                .build();
    }
}

