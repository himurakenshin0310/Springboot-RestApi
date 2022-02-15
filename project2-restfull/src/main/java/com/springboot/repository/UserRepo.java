package com.springboot.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.entity.User;
@Repository
@Qualifier("UserRepo")
public interface UserRepo extends JpaRepository<User, Long> {
	@Query(value = "select u from User u join fetch u.userRole where u.email = ?1")
	User findByEmail(String email);
}
