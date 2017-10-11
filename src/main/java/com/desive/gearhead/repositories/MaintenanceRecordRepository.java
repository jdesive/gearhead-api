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

package com.desive.gearhead.repositories;

import com.desive.gearhead.entities.Car;
import com.desive.gearhead.entities.MaintenanceRecord;
import com.desive.gearhead.repositories.interfaces.IMaintenanceRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer>, JpaSpecificationExecutor<MaintenanceRecord>, IMaintenanceRecordRepository {

    Page<MaintenanceRecord> findByCar(Car car, Pageable pageable);

    Page<MaintenanceRecord> findByTimestamp(Date date, Pageable pageable);

}
