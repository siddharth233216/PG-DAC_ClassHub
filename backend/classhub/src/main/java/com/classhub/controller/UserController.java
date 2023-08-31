package com.classhub.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classhub.dto.ApiResponse;
import com.classhub.dto.AuthRequest;
import com.classhub.dto.Userdto;
import com.classhub.jwt_utils.JwtUtils;
import com.classhub.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	// dep : auth mgr
	@Autowired
	private AuthenticationManager authMgr;

	// dep : jwt utils
	@Autowired
	private JwtUtils jwtUtils;

	// http://host:port/users/signin , method=POST
	@PostMapping("/signin")
	public ResponseEntity<?> signinUser(@RequestBody @Valid AuthRequest dto) {
		System.out.println("in signin user " + dto);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(),
				dto.getPassword());
		System.out.println("auth obj before " + token);

		Authentication verifiedPrincipal = authMgr.authenticate(token);
		System.out.println("principal " + verifiedPrincipal);
		String jwtToken = jwtUtils.generateJwtToken(verifiedPrincipal);

//		if (verifiedPrincipal.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
//			String targetUrl = "/admin/home";
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Authorization", "Bearer " + jwtToken);
//			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(targetUrl)).headers(headers).body(dto);
//		} else if (verifiedPrincipal.getAuthorities().stream()
//				.anyMatch(auth -> auth.getAuthority().equals("ROLE_FACULTY"))) {
//			String targetUrl = "/faculty/home";
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Authorization", "Bearer " + jwtToken);
//			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(targetUrl)).headers(headers).body(dto);
//		} else {
//			String targetUrl = "/faculty/home";
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Authorization", "Bearer " + jwtToken);
//			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(targetUrl)).headers(headers).body(dto);
//		}
		return ResponseEntity.ok(new ApiResponse(jwtToken));
	}

	@PostMapping("/add")
	public void addingUser(@RequestBody Userdto new_user) {
		userService.addUser(new_user);
	}

}