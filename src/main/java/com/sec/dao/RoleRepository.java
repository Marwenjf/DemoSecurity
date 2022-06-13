package com.sec.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
