package com.classhub.service;

import com.classhub.dto.Userdto;

public interface UserService {

	Userdto getUser(String email);

	void addUser(Userdto new_user);
}
