package com.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Role;
import com.springboot.entity.User;
import com.springboot.repository.RoleRepo;
import com.springboot.repository.UserRepo;

@Service
@Transactional
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		User user = userRepo.findByEmail(userName);
		Role role = roleRepo.findByRoleName(roleName);

	}

	@Override
	public User getUser(String userName) {
		return userRepo.findByEmail(userName);
	}

	@Override
	public List<User> getUser() {
		return userRepo.findAll();
	}

}
