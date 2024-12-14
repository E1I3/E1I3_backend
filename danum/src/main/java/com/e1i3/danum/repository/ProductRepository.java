package com.e1i3.danum.repository;

import com.e1i3.danum.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
