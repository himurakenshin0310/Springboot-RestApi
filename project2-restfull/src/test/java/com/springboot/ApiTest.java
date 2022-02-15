package com.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.entity.UserRoles;
import com.springboot.repository.UserRepo;
import com.springboot.securityconfig.JwtTokenProvider;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiTest {
	@Autowired
	@Qualifier("UserRepo")
	UserRepo userRepo;

	@Test
	public void testUserShouldReturnValue() {
		assertThat(userRepo.findById(1l).get().getEmail()).isNotNull();
	}

	@Test
	public void testUserGetRoleNotNull() {
		assertThat(userRepo.findById(1l).get().getUserRole()).isNotNull();
	}

	@Autowired
	JwtTokenProvider tokenProvier;

	@Test
	public void testVerifyJwtToken() {
		try {
			assertThat(tokenProvier.verifyToken(
					"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQGdtYWlsLmNvbSIsImV4cCI6MTY0NTE5MDM2MCwiaWF0IjoxNjQ0OTMxMTYwfQ.rOVBtdJ_h8bZGWoBIRKOzT5aUJ6UhG5Yoj-jw05e3sFcbORqAeza53swpEVznWj6jujs-ZpOb6STsw1R0t3cJA"))
							.isTrue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
