package com.springboot.securityconfig;

import com.springboot.model.UserDto;

public interface UserService {
	public void registUser(UserDto user);

	public boolean checkUserName(String userName);
}
