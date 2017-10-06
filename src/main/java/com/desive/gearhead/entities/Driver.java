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

package com.desive.gearhead.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 Created by Jack DeSive on 10/1/2017 at 9:59 PM
*/
@Entity
@Table(name = "drivers")
@JsonPropertyOrder({"id"})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverid;
    @Column(nullable = false, unique = true)
    private String licenseNumber;
    @Column(nullable = false)
    private String name;
    private String dlClass = "";
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss")
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "drivers")
    @JsonIgnoreProperties("drivers")
    private Set<Car> cars = new HashSet<>();

    public Driver() {
    }

    public Driver(String licenseNumber, String name, String dlClass, Date dateOfBirth, Set<Car> cars) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.dlClass = dlClass;
        this.dateOfBirth = dateOfBirth;
        this.cars = cars;
    }

    public int getId() {
        return driverid;
    }

    public void setId(int driverid) {
        this.driverid = driverid;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDlClass() {
        return dlClass;
    }

    public void setDlClass(String dlClass) {
        this.dlClass = dlClass;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> car) {
        this.cars = car;
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
