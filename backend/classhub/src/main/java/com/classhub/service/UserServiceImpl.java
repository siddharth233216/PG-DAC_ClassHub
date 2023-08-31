package com.classhub.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.classhub.custom_exceptions.ResourceNotFoundException;
import com.classhub.dto.Userdto;
import com.classhub.entities.User;
import com.classhub.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userrepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@Override
	public Userdto getUser(String email) {
		User user = userrepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
		return mapper.map(user, Userdto.class);
	}


	@Override
	public void addUser(Userdto new_user) {
		// TODO Auto-generated method stub
		User user = mapper.map(new_user, User.class);
		user.setPassword(encoder.encode(user.getPassword()));
		userrepo.save(user);
		
	}

}
