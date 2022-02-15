package com.springboot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.springboot.entity.User;
import com.springboot.entity.UserRoles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String userName;
	private String passWord;
	private String name;
	private String phone;
	private List<Long> roleId;
	private boolean enable;

	public static UserDto create(User user) {
		UserDto u = new UserDto();
		u.setEnable(user.isEnable());
		u.setName(user.getName());
		u.setPassWord(user.getPassword());
		u.setPhone(user.getPhone());
		u.setUserName(user.getEmail());
		List<Long> lstRoleId = new ArrayList<Long>();
		for(UserRoles i : user.getUserRole()) {
			lstRoleId.add(i.getRoleId().getId());
		}		
		u.setRoleId(lstRoleId);
		return u;
	}
}
