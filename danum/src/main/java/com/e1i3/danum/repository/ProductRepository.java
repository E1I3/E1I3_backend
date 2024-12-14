package com.e1i3.danum.repository;

import com.e1i3.danum.entity.Product;
import com.e1i3.danum.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStore(Store store);
}
