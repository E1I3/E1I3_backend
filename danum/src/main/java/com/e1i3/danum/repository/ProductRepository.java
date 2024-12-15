package com.e1i3.danum.repository;

import com.e1i3.danum.entity.Product;
import com.e1i3.danum.entity.Store;
import com.e1i3.danum.enumeration.TradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStore(Store store);
    @Query("SELECT p FROM products p WHERE p.store.storeId = :storeId")
    List<Product> findByStoreId(Long storeId);
    List<Product> findByStoreAndTradeType(Store store, TradeType trade);
}
