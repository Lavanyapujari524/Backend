package com.nexushr.nexushr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexushr.nexushr.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}