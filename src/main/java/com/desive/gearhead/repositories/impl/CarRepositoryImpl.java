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

package com.desive.gearhead.repositories.impl;

import com.desive.gearhead.entities.Car;
import com.desive.gearhead.repositories.CarRepository;
import com.desive.gearhead.repositories.criteria.CarSearchCriteria;
import com.desive.gearhead.repositories.interfaces.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.desive.gearhead.repositories.criteria.CarSearchCriteria.*;
import static org.springframework.data.jpa.domain.Specifications.where;

/*
 Created by Jack DeSive on 10/2/2017 at 10:55 PM
*/
public class CarRepositoryImpl implements ICarRepository {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Page<Car> findByCriteria(CarSearchCriteria criteria, Pageable pageable) {
        if(criteria.isEmpty())
            return carRepository.findAll(pageable);
        return carRepository.findAll(where(withId(criteria.getId()))
                .and(withMake(criteria.getMake()))
                .and(withModel(criteria.getModel()))
                .and(withColor(criteria.getColor()))
                .and(withVin(criteria.getVin()))
                .and(withPlateNumber(criteria.getPlateNumber()))
                .and(withDotNumber(criteria.getDotNumber()))
                .and(withOilType(criteria.getOilType()))
                .and(withOilFilterModel(criteria.getOilFilterModel()))
                .and(withAirFilterModel(criteria.getAirFilterModel()))
                .and(withCabinFilterModel(criteria.getCabinFilterModel()))
                .and(withBatteryModel(criteria.getBatteryModel()))
                .and(withDotRegistered(criteria.isDotRegistered())),
                pageable);
    }

}
