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

import com.desive.gearhead.entities.MaintenanceRecord;
import com.desive.gearhead.repositories.MaintenanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MaintenanceRecordController {

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/maintenance/all", params = {"page", "size"})
    private Page<MaintenanceRecord> listMaintenanceRecords(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "20") int size){
        return this.maintenanceRecordRepository.findAll(new PageRequest(page, size));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/maintenance/{date}")
    private Page<MaintenanceRecord> listMaintenanceRecordsByDate(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "20") int size,
                                                           @PathVariable(name = "date") Date date){
        return this.maintenanceRecordRepository.findByTimestamp(date, new PageRequest(page, size));
    }

}
