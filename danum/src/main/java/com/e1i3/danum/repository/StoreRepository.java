package com.e1i3.danum.repository;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.repository.custom.StoreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> , StoreRepositoryCustom {

}
