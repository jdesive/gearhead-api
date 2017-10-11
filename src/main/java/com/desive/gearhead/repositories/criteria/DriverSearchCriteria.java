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

package com.desive.gearhead.repositories.criteria;

import com.desive.gearhead.entities.Driver;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;

/*
 Created by Jack DeSive on 10/2/2017 at 6:28 PM
*/
public class DriverSearchCriteria {

    String name, licenseNumber;
    Integer id, carid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public boolean isEmpty(){
        if(name != null || licenseNumber != null || carid != null)
            return false;
        return true;
    }

    public static Specification<Driver> withId(Integer id){
        if(id != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("driverid"), id)));
        }
        return null;
    }

    public static Specification<Driver> withName(String name){
        if(name != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name)));
        }
        return null;
    }

    public static Specification<Driver> withLicenseNumber(String number){
        if(number != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("licenseNumber"), number)));
        }
        return null;
    }

    public static Specification<Driver> withCarId(Integer carid){
        if(carid != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("cars").get("carsid"), carid)));
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(this.getClass().getSimpleName()).append("[ ");
        Arrays.stream(getClass().getDeclaredFields()).filter(field -> {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if(value != null)
                    return true;
            } catch (IllegalAccessException e) {}
            return false;
        }).forEach(field -> {
            try {
                stringBuilder.append(field.getName()).append("=").append(field.get(this)).append(" ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return stringBuilder.append("]").toString();
    }

}
