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

package com.desive.gearhead.repositories.impl;

import com.desive.gearhead.entities.User;
import com.desive.gearhead.repositories.UserRepository;
import com.desive.gearhead.repositories.criteria.UserSearchCriteria;
import com.desive.gearhead.repositories.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.desive.gearhead.repositories.criteria.UserSearchCriteria.*;
import static org.springframework.data.jpa.domain.Specifications.where;

/*
 Custom JPA query logic

 Created by Jack DeSive on 10/3/2017 at 3:17 AM
*/
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findByCriteria(UserSearchCriteria criteria, Pageable pageable) {
        if(criteria.isEmpty())
            return userRepository.findAll(pageable);
        return userRepository.findAll(where(withId(criteria.getId()))
                .and(withUsername(criteria.getUsername()))
                .and(withIsEnabled(criteria.getEnabled())),
                pageable);
    }

}
