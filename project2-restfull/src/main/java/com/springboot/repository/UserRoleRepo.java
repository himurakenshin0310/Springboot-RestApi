package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.UserRoles;
@Repository
public interface UserRoleRepo extends JpaRepository<UserRoles, Long> {

}
