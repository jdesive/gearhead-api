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

import com.desive.gearhead.entities.CarNote;
import com.desive.gearhead.repositories.CarNoteRepository;
import com.desive.gearhead.repositories.criteria.CarNoteSearchCriteria;
import com.desive.gearhead.repositories.interfaces.ICarNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.desive.gearhead.repositories.criteria.CarNoteSearchCriteria.*;
import static org.springframework.data.jpa.domain.Specifications.where;

/*
 Created by Jack DeSive on 10/2/2017 at 10:55 PM
*/
public class CarNoteRepositoryImpl implements ICarNoteRepository {

    @Autowired
    private CarNoteRepository carNoteRepository;

    @Override
    public Page<CarNote> findByCriteria(CarNoteSearchCriteria criteria, Pageable pageable) {
        if(criteria.isEmpty())
            return carNoteRepository.findAll(pageable);
        return carNoteRepository.findAll(where(withId(criteria.getId()))
                .and(withCarId(criteria.getCarid()))
                .and(withStartdate(criteria.getStartdate()))
                .and(withEnddate(criteria.getEnddate())),
                pageable);
    }

}
