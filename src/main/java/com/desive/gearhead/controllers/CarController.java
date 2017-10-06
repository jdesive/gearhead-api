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
import com.desive.gearhead.repositories.CarRepository;
import com.desive.gearhead.repositories.criteria.CarSearchCriteria;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired private CarRepository carRepository;
    private Logger logger = LoggerFactory.getLogger(CarController.class);

    @ApiOperation(tags = {"Cars"}, value = "Search for Cars", nickname = "Search", produces = "applications/json")
    @RequestMapping(method = RequestMethod.GET, value = "/cars", produces = "application/json")
    private Page<Car> searchCars(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                               @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                               @RequestParam(name = "id", required = false) Integer carid,
                               @RequestParam(name = "make", required = false) String make,
                               @RequestParam(name = "model", required = false) String model,
                               @RequestParam(name = "color", required = false) String color,
                               @RequestParam(name = "vin", required = false) String vin,
                               @RequestParam(name = "plate", required = false) String plate,
                               @RequestParam(name = "dot", required = false) String dot,
                               @RequestParam(name = "oilType", required = false) String oilType,
                               @RequestParam(name = "oilFilterModel", required = false) String oilFilterModel,
                               @RequestParam(name = "airFilterModel", required = false) String airFilterModel,
                               @RequestParam(name = "cabinFilterModel", required = false) String cabinFilterModel,
                               @RequestParam(name = "batteryModel", required = false) String batteryModel,
                               @RequestParam(name = "dotRegistered", required = false) Boolean dotRegistered){
        logger.debug("Building car search criteria...");
        CarSearchCriteria criteria = new CarSearchCriteria();
        if(carid != null)
            criteria.setId(carid);
        if(make != null)
            criteria.setMake(make);
        if(model != null)
            criteria.setModel(model);
        if(color != null)
            criteria.setColor(color);
        if(vin != null)
            criteria.setVin(vin);
        if(plate != null)
            criteria.setPlateNumber(plate);
        if(dot != null)
            criteria.setDotNumber(dot);
        if(oilType != null)
            criteria.setOilType(oilType);
        if(oilFilterModel != null)
            criteria.setOilFilterModel(oilFilterModel);
        if(airFilterModel != null)
            criteria.setAirFilterModel(airFilterModel);
        if(cabinFilterModel != null)
            criteria.setCabinFilterModel(cabinFilterModel);
        if(batteryModel != null)
            criteria.setBatteryModel(batteryModel);
        if(dotRegistered != null)
            criteria.setDotRegistered(dotRegistered);
        if(make != null)
            criteria.setMake(make);
        if(make != null)
            criteria.setMake(make);
        logger.debug("Querying cars table by [{}]", criteria.toString());
        return this.carRepository.findByCriteria(criteria, new PageRequest(page, size));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = {"Cars"}, value = "Register a Car", nickname = "Register", produces = "applications/json")
    @RequestMapping(method = RequestMethod.POST, value = "/cars", produces = "application/json")
    private Car registerCar(@RequestBody Car car){
        logger.debug("Inserting [{}] into cars table", car.toString());
        return this.carRepository.save(car);
    }

}
