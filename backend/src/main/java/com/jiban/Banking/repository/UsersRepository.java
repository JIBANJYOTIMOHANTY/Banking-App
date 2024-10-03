package com.jiban.Banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiban.Banking.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String name)   ;
}
