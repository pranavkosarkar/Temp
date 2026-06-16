package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
