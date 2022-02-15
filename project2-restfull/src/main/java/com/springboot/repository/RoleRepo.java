package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	@Query(value = "select r from Role r join fetch r.userRole where r.roleName = ?1")
	Role findByRoleName(String roleName);
}
