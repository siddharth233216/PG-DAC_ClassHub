package com.classhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classhub.dto.AuthRequest;
import com.classhub.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/home")
	public ResponseEntity<?> adminHome(@RequestHeader("Authorization") String authorizationHeader,@RequestBody AuthRequest dto) {
		String jwtToken = authorizationHeader.substring(7);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + jwtToken);
		return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(userService.getUser(dto.getEmail()));
		
	}
}
