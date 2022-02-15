package com.springboot.securityconfig.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.securityconfig.JwtTokenProvider;
import com.springboot.securityconfig.UserDetailServiceImp;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtauthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenProvider jwtToken;

	@Autowired
	UserDetailServiceImp userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// lay jwt tu request
			String jwt = getJwtFormRequest(request);
			log.info("jwttoken {}", jwt);
			// neu co jwt va verify thanh cong
			if (StringUtils.hasText(jwt) && jwtToken.verifyToken(jwt)) {
				// lay thong tin nguoi dung tu jwt
				String username = jwtToken.getUserNameFromToken(jwt);
				UserDetails userDeitals = userDetailService.loadUserByUsername(username);
				if (userDeitals != null) {
					// neu nguoi dung hop le set thong tin vao security context
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDeitals, null, userDeitals.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			log.error("failed on set user authentication {}", e);
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtFormRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
