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

import com.desive.gearhead.entities.Driver;
import com.desive.gearhead.repositories.DriverRepository;
import com.desive.gearhead.repositories.criteria.DriverSearchCriteria;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*
 Created by Jack DeSive on 10/1/2017 at 11:08 PM
*/
@RestController
public class DriverController {

    @Autowired private DriverRepository driverRepository;
    private Logger logger = LoggerFactory.getLogger(DriverController.class);

    @ApiOperation(tags = {"Drivers"}, value = "Search for Drivers", nickname = "Search", produces = "applications/json")
    @RequestMapping(method = RequestMethod.GET, value = "/drivers", produces = {"application/json"})
    private Page<Driver> searchDrivers(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                  @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                  @RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "license", required = false) String license,
                                  @RequestParam(name = "carid", required = false) Integer carid,
                                  @RequestParam(name = "id", required = false) Integer id){
        logger.debug("Building driver search criteria...");
        DriverSearchCriteria criteria = new DriverSearchCriteria();
        if(id != null)
            criteria.setId(id);
        if(name != null)
            criteria.setName(name);
        if(license != null)
            criteria.setLicenseNumber(license);
        if(carid != null)
            criteria.setCarid(carid);
        logger.debug("Querying drivers table by [{}]", criteria.toString());
        return this.driverRepository.findByCriteria(criteria, new PageRequest(page, size));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = {"Drivers"}, value = "Register a Driver", nickname = "Register", produces = "applications/json")
    @RequestMapping(method = RequestMethod.POST, value = "/drivers", produces = {"application/json"})
    private Driver registerDriver(@RequestBody Driver driver){
        logger.debug("Inserting [{}] into drivers table", driver.toString());
        return this.driverRepository.save(driver);
    }

}
