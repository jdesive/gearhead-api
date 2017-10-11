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
import com.desive.gearhead.entities.CarNote;
import com.desive.gearhead.exceptions.CarNotFoundException;
import com.desive.gearhead.repositories.CarNoteRepository;
import com.desive.gearhead.repositories.CarRepository;
import com.desive.gearhead.repositories.criteria.CarNoteSearchCriteria;
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

/*
 Created by Jack DeSive on 10/1/2017 at 11:08 PM
*/
@RestController
public class CarNoteController {

    @Autowired private CarRepository carRepository;
    @Autowired private CarNoteRepository carNoteRepository;
    private Logger logger = LoggerFactory.getLogger(CarNoteController.class);

    @ApiOperation(tags = {"Car Notes"}, value = "Search for Car Notes", nickname = "Search", produces = "applications/json")
    @RequestMapping(method = RequestMethod.GET, value = "/carnotes", produces = {"application/json"})
    private Page<CarNote> searchCarNotes(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                      @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                      @RequestParam(name = "id", required = false) Integer id,
                                      @RequestParam(name = "carid", required = false) Integer carid,
                                      @RequestParam(name = "startdate", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date startdate,
                                      @RequestParam(name = "enddate", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date enddate){
        logger.debug("Building car note search criteria...");
        CarNoteSearchCriteria criteria = new CarNoteSearchCriteria();
        if(id != null)
            criteria.setId(id);
        if(carid != null)
            criteria.setCarid(carid);
        if(startdate != null)
            criteria.setStartdate(startdate);
        if(enddate != null)
            criteria.setEnddate(enddate);
        logger.debug("Querying car_notes table by [{}]", criteria.toString());
        return this.carNoteRepository.findByCriteria(criteria, new PageRequest(page, size));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = {"Car Notes"}, value = "Register a Car Note", nickname = "Register", produces = "applications/json")
    @RequestMapping(method = RequestMethod.POST, value = "/carnotes/{carid}", produces = {"application/json"})
    private CarNote registerCarNote(@PathVariable("carid")int carid, @RequestBody CarNote note){
        logger.debug("Querying cars table for car with id [{}]", carid);
        Car car = this.carRepository.findOne(carid);
        if(car == null)
            throw new CarNotFoundException(carid);
        note.setCar(car);
        logger.debug("Inserting [{}] into car_notes table referencing [{}]", note.toString(), car.toString());
        return this.carNoteRepository.save(note);
    }

}
