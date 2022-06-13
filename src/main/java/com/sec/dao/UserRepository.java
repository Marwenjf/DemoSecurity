package com.sec.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
