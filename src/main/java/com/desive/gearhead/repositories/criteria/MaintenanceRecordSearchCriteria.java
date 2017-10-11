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

import com.desive.gearhead.entities.MaintenanceRecord;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Date;

/*
 Created by Jack DeSive on 10/4/2017 at 9:55 PM
*/
public class MaintenanceRecordSearchCriteria {

    private Integer id, carid;
    private String actiontaken, maintainer;
    private Date startdate, enddate;

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

    public String getActiontaken() {
        return actiontaken;
    }

    public void setActiontaken(String actiontaken) {
        this.actiontaken = actiontaken;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public boolean isEmpty() {
        if(id != null || carid != null || actiontaken != null || maintainer != null || startdate != null || enddate != null)
            return false;
        return true;
    }

    public static Specification<MaintenanceRecord> withId(Integer id){
        if(id != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("recordid"), id)));
        }
        return null;
    }

    public static Specification<MaintenanceRecord> withCarId(Integer carid){
        if(carid != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("car").get("carsid"), carid)));
        }
        return null;
    }

    public static Specification<MaintenanceRecord> withActiontaken(String actiontaken){
        if(actiontaken != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("actiontaken"), actiontaken)));
        }
        return null;
    }

    public static Specification<MaintenanceRecord> withMaintainer(String maintainer){
        if(maintainer != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("maintainer"), maintainer)));
        }
        return null;
    }

    public static Specification<MaintenanceRecord> withStartdate(Date startdate){
        if(startdate != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("timestamp"), startdate)));
        }
        return null;
    }

    public static Specification<MaintenanceRecord> withEnddate(Date enddate){
        if(enddate != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("timestamp"), enddate)));
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
