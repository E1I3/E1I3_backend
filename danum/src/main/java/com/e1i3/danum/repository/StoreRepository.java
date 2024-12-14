package com.e1i3.danum.repository;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByUser(User currentUser);
}
