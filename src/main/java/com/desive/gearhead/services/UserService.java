package com.desive.gearhead.services;

import com.desive.gearhead.dto.UserDto;
import com.desive.gearhead.entities.RoleName;

import java.util.List;

public interface UserService {

	void createUser(UserDto user, RoleName roleName);

	void updateUser(UserDto user);

	void deleteUser(Integer id);

	UserDto getUser(Integer id);

	List<UserDto> getUsers();

}
