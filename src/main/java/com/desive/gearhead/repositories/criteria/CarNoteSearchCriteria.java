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

package com.desive.gearhead.repositories.criteria;

import com.desive.gearhead.entities.CarNote;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Date;

/*
 Created by Jack DeSive on 10/2/2017 at 9:26 PM
*/
// ... excluseNulls = true PLEASE
public class CarNoteSearchCriteria {

    private Integer id, carid;
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

    public boolean isEmpty(){
        if(id != null || carid != null || startdate != null || enddate != null)
            return false;
        return true;
    }

    public static Specification<CarNote> withId(Integer id){
        if(id != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("noteid"), id)));
        }
        return null;
    }

    public static Specification<CarNote> withCarId(Integer carid){
        if(carid != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("car").get("carsid"), carid)));
        }
        return null;
    }

    public static Specification<CarNote> withStartdate(Date startdate){
        if(startdate != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("timestamp"), startdate)));
        }
        return null;
    }

    public static Specification<CarNote> withEnddate(Date enddate){
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
