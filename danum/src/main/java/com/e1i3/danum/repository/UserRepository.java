package com.e1i3.danum.repository;

import com.e1i3.danum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
