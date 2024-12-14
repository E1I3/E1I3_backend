package com.e1i3.danum.repository;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.repository.custom.StoreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
    Optional<Store> findByUser(User currentUser);
    @Query("SELECT s FROM stores s WHERE s.user.userId = :userId")
    Store findByUserId(Long userId);
}
