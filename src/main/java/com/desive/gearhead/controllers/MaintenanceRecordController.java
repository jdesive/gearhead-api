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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MaintenanceRecordController {

    @Autowired private CarRepository carRepository;
    @Autowired private MaintenanceRecordRepository maintenanceRecordRepository;
    private Logger logger = LoggerFactory.getLogger(MaintenanceRecordController.class);

    @ApiOperation(tags = {"Maintenance Record"}, value = "Search Maintenance Records", nickname = "Search", produces = "applications/json")
    @RequestMapping(method = RequestMethod.GET, value = "/maintenance", produces = "application/json")
    private Page<MaintenanceRecord> searchMaintenanceRecords(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                           @RequestParam(name = "size", required = false, defaultValue = "20") int size,
                                                           @RequestParam(name = "id", required = false) Integer id,
                                                           @RequestParam(name = "carid", required = false) Integer carid,
                                                           @RequestParam(name = "actiontaken", required = false) String actiontaken,
                                                           @RequestParam(name = "maintainer", required = false) String maintainer,
                                                           @RequestParam(name = "startdate", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date startdate,
                                                           @RequestParam(name = "enddate", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date enddate){
        logger.debug("Building maintenance record search criteria...");
        MaintenanceRecordSearchCriteria criteria = new MaintenanceRecordSearchCriteria();
        if(id != null)
            criteria.setId(id);
        if(carid != null)
            criteria.setCarid(carid);
        if(actiontaken != null)
            criteria.setActiontaken(actiontaken);
        if(maintainer != null)
            criteria.setMaintainer(maintainer);
        if(startdate != null)
            criteria.setStartdate(startdate);
        if(enddate != null)
            criteria.setEnddate(enddate);
        logger.debug("Querying maintenance_records table by [{}]", criteria.toString());
        return this.maintenanceRecordRepository.findByCriteria(criteria, new PageRequest(page, size));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = {"Maintenance Record"}, value = "Register a Maintenance Record", nickname = "Register", produces = "applications/json")
    @RequestMapping(method = RequestMethod.POST, value = "/maintenance/{carid}", produces = "application/json")
    private MaintenanceRecord registerMaintenanceRecord(@PathVariable("carid") int carid, @RequestBody MaintenanceRecord record){
        logger.debug("Querying cars table for car with id [{}]", carid);
        Car car = this.carRepository.findOne(carid);
        if(car == null)
            throw new CarNotFoundException(carid);
        record.setCar(car);
        logger.debug("Inserting [{}] into maintenance_records table referencing [{}]", record.toString(), car.toString());
        return this.maintenanceRecordRepository.save(record);
    }

}
