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
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/*
 Created by Jack DeSive on 10/1/2017 at 9:48 PM
*/
@Entity
@ToString
@Table(name = "car_notes")
public class CarNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    @JsonIgnoreProperties("notes")
    private Car car;

    private String note = "";
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date timestamp;

    public CarNote() {
    }

    public CarNote(Car car, String note, Date timestamp) {
        this.car = car;
        this.note = note;
        this.timestamp = timestamp;
    }

    @PrePersist
    public void prePersist() {
        timestamp = new Date(System.currentTimeMillis());
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
