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

import com.desive.gearhead.entities.Driver;
import com.desive.gearhead.repositories.DriverRepository;
import com.desive.gearhead.repositories.criteria.DriverSearchCriteria;
import com.desive.gearhead.repositories.interfaces.IDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.desive.gearhead.repositories.criteria.DriverSearchCriteria.*;
import static org.springframework.data.jpa.domain.Specifications.where;

/*
 Created by Jack DeSive on 10/2/2017 at 10:55 PM
*/
public class DriverRepositoryImpl implements IDriverRepository {

    @Autowired private DriverRepository driverRepository;

    @Override
    public Page<Driver> findByCriteria(DriverSearchCriteria criteria, Pageable pageable) {
        if(criteria.isEmpty())
            return driverRepository.findAll(pageable);
        return driverRepository.findAll(where(withName(criteria.getName()))
                .and(withLicenseNumber(criteria.getLicenseNumber()))
                .and(withCarId(criteria.getCarid())),
                pageable);
    }

}
