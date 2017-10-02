/*
 * Copyright (C) 2017  GearHead
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.desive.gearhead.controllers;

import com.desive.gearhead.entities.User;
import com.desive.gearhead.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET, params = {"username", "password"})
	public ObjectNode signin(@RequestParam("username")String username, @RequestParam("password")String password){
		ObjectNode response = objectMapper.createObjectNode();

		if(userRepository.findByUsername(username) == null) {
			response.put("successful", false);
			response.put("error", "Username or password does not exist!");
			return response;
		}

		User user = userRepository.findByUsername(username);
		if(!user.getPassword().equals(password)) {
			response.put("successful", false);
			response.put("error", "Incorrect password!");
		}else{
			response.put("successful", true);
			response.put("error", "null");
		}
		return response;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET, params = {"username", "password"})
	public ObjectNode signup(@RequestParam("username")String username, @RequestParam("password")String password){
		ObjectNode response = objectMapper.createObjectNode();
		if(userRepository.findByUsername(username) != null){
			response.put("successful", false);
			response.put("error", "Sorry a user with that name already exists.");
		}else if(password.length() >= 8){
			response.put("successful", true);
			userRepository.save(new User(username, password));
		}else{
			response.put("successful", false);
			response.put("error", "Your password must be at-least 8 characters long.");
		}
		return response;
	}

}
