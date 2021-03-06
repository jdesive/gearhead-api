/*
 * Copyright (C) 2017  GearHead
 *
 * This file is part of GearHead API.
 *
 * GearHead API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.desive.gearhead.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(of = { "username", "enabled" })
@ToString(of = { "id", "username" })
@Entity
@Table(name = "users")
@JsonPropertyOrder({"id"})
public class User {

	public static final int MAX_LENGTH_USERNAME = 30;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;

	@Column(nullable = false, unique = true, length = MAX_LENGTH_USERNAME)
	private String username = "";

	@Column(nullable = false)
	private String password = "";

	private boolean enabled = true;
	@JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss")
	private Date creationTime = new Date(System.currentTimeMillis());

	@JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss")
	private Date modificationTime;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.enabled = true;
	}

	@PreUpdate
	public void preUpdate() {
		modificationTime = new Date(System.currentTimeMillis());
	}

	public Integer getId() {
		return userid;
	}

	public void setId(Integer id) {
		this.userid = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

}
