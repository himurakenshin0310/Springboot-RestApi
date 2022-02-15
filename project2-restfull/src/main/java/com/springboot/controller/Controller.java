package com.springboot.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.entity.User;
import com.springboot.entity.UserRoles;
import com.springboot.model.UserDto;
import com.springboot.repository.UserRoleRepo;
import com.springboot.securityconfig.JwtTokenProvider;
import com.springboot.securityconfig.UserCustomDeitail;
import com.springboot.service.UserService;

import lombok.Data;

@RestController
public class Controller {

	@Autowired
	UserService userService;

	@Autowired
	UserRoleRepo userRoleRepo;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtToken;

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAll() {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getUser().stream().map(UserDto::create).collect(Collectors.toList()));
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> postLogin(@RequestBody loginRequest user) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Map<String, Object> map = new HashMap<>();
		map.put("access_token", jwtToken.generateToken((UserCustomDeitail) authentication.getPrincipal()));
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	@PostMapping("/user/save")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
		User u = new User();
		u.setEmail(user.getUserName());
		u.setEnable(true);
		u.setName(user.getName());
		u.setPassword(user.getPassWord());
		u.setPhone(user.getPhone());
		Set<UserRoles> roles = new HashSet<UserRoles>();
		user.getRoleId().forEach(s -> roles.add(userRoleRepo.findById(s).get()));
		u.setUserRole(roles);
		return ResponseEntity.status(HttpStatus.OK).body(UserDto.create(userService.saveUser(u)));
	}

}

@Data
class loginRequest {
	String username;
	String password;
}
