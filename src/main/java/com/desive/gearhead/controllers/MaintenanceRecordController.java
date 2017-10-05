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

import com.desive.gearhead.entities.Car;
import com.desive.gearhead.entities.MaintenanceRecord;
import com.desive.gearhead.exceptions.CarNotFoundException;
import com.desive.gearhead.repositories.CarRepository;
import com.desive.gearhead.repositories.MaintenanceRecordRepository;
import com.desive.gearhead.repositories.criteria.MaintenanceRecordSearchCriteria;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaintenanceRecordController {

    @Autowired private CarRepository carRepository;
    @Autowired private MaintenanceRecordRepository maintenanceRecordRepository;
    private Logger logger = LoggerFactory.getLogger(MaintenanceRecordController.class);


    @ApiOperation(tags = {"Maintenance Record"}, value = "Search Maintenance Records", nickname = "Search", produces = "applications/json")
    @RequestMapping(method = RequestMethod.GET, value = "/maintenance", produces = "application/json")
    private Page<MaintenanceRecord> listMaintenanceRecords(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                           @RequestParam(name = "size", required = false, defaultValue = "20") int size){
        MaintenanceRecordSearchCriteria criteria = new MaintenanceRecordSearchCriteria();
        return this.maintenanceRecordRepository.findByCriteria(criteria, new PageRequest(page, size));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = {"Maintenance Record"}, value = "Register a Maintenance Record", nickname = "Register", produces = "applications/json")
    @RequestMapping(method = RequestMethod.POST, value = "/maintenance/{carid}", produces = "application/json")
    private MaintenanceRecord addMaintenanceRecord(@PathVariable("carid") int carid, @RequestBody MaintenanceRecord record){
        logger.debug(String.format("Querying cars table for car with id %d", carid));
        Car car = this.carRepository.findOne(carid);
        if(car == null)
            throw new CarNotFoundException(carid);
        record.setCar(car);
        logger.debug(String.format("Inserting %s into maintenance_records table referencing %s ", record.toString(), car.toString()));
        return this.maintenanceRecordRepository.save(record);
    }

}
