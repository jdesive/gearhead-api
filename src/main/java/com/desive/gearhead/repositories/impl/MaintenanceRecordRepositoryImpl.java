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

import com.desive.gearhead.entities.MaintenanceRecord;
import com.desive.gearhead.repositories.MaintenanceRecordRepository;
import com.desive.gearhead.repositories.criteria.MaintenanceRecordSearchCriteria;
import com.desive.gearhead.repositories.interfaces.IMaintenanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.desive.gearhead.repositories.criteria.MaintenanceRecordSearchCriteria.*;
import static org.springframework.data.jpa.domain.Specifications.where;

/*
 Created by Jack DeSive on 10/2/2017 at 10:55 PM
*/
public class MaintenanceRecordRepositoryImpl implements IMaintenanceRecordRepository {

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @Override
    public Page<MaintenanceRecord> findByCriteria(MaintenanceRecordSearchCriteria criteria, Pageable pageable) {
        if(criteria.isEmpty())
            return maintenanceRecordRepository.findAll(pageable);
        return maintenanceRecordRepository.findAll(where(withId(criteria.getId()))
                .and(withCarId(criteria.getCarid()))
                .and(withActiontaken(criteria.getActiontaken()))
                .and(withMaintainer(criteria.getMaintainer()))
                .and(withStartdate(criteria.getStartdate()))
                .and(withEnddate(criteria.getEnddate())),
                pageable);
    }

}
