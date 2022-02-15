package com.springboot.securityconfig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.entity.User;
import com.springboot.entity.UserRoles;
import com.springboot.model.UserDto;
import com.springboot.repository.UserRepo;
import com.springboot.repository.UserRoleRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceSecurityImp implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder passwordEncode;

	@Autowired
	UserRoleRepo userRoleRepo;

	@Override
	public void registUser(UserDto user) {
		if (checkUserName(user.getUserName())) {
			User u = new User();
			u.setEmail(user.getUserName());
			u.setEnable(true);
			u.setName(user.getName());
			u.setPassword(passwordEncode.encode(user.getPassWord()));
			u.setPhone(user.getPhone());
			// role admin is 1, role user is 2
			Set<UserRoles> role = new HashSet<UserRoles>();
			role.add(userRoleRepo.findById(2l).get());
			u.setUserRole(role);
			userRepo.save(u);
		}else
			log.error("this username already exist!");
	}

	@Override
	public boolean checkUserName(String userName) {
		if (userRepo.findByEmail(userName) == null)
			return true;
		else
			return false;
	}

}
