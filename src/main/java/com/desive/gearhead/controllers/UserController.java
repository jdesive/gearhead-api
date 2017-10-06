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
import com.desive.gearhead.repositories.criteria.UserSearchCriteria;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

	@Autowired private UserRepository userRepository;
	@Autowired private ObjectMapper objectMapper;
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(tags = {"Users"}, value = "Search for Users", nickname = "Search", produces = "applications/json")
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = {"application/json"})
	public Page<User> searchUsers(@RequestParam(value = "username", required = false) String username,
								  @RequestParam(value = "id", required = false) Integer id,
								  @RequestParam(value = "enabled", required = false) Boolean enabled,
								  @RequestParam(value = "page", required = false, defaultValue = "0") int page,
								  @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		logger.debug("Building user search criteria...");
		UserSearchCriteria criteria = new UserSearchCriteria(id, username, enabled);
		logger.debug("Querying users table by [{}]", criteria.toString());
		return userRepository.findByCriteria(criteria, new PageRequest(page, size));
	}

	@ApiOperation(tags = {"Users"}, value = "Check Sign In credentials", nickname = "Sign In", produces = "applications/json")
	@RequestMapping(value = "/signin", method = RequestMethod.GET, produces = {"application/json"})
	public ObjectNode signin(@RequestParam("username")String username, @RequestParam("password")String password){
		logger.debug("Building signin response object...");
		ObjectNode response = objectMapper.createObjectNode();
		User user = userRepository.findByUsername(username);

		if(user == null || !user.getPassword().equals(password)) {
			response.put("successful", false);
			response.put("error", "Username or password is incorrect!");
			return response;
		}else{
			response.put("successful", true);
			response.put("error", "null");
		}
		return response;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(tags = {"Users"}, value = "Sign Up to get credentials", nickname = "Sign Up", produces = "applications/json")
	@RequestMapping(value = "/signup", method = RequestMethod.GET, produces = {"application/json"})
	public ObjectNode signup(@RequestParam("username")String username, @RequestParam("password")String password){
		logger.debug("Building signup response object...");
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
