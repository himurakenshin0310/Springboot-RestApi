package com.springboot.service;

import java.util.List;

import com.springboot.entity.Role;
import com.springboot.entity.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String userName, String roleName);
	User getUser(String userName);
	List<User> getUser();
}
