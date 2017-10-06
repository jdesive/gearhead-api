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
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Table(name = "maintenance_records")
@JsonPropertyOrder({"id"})
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car", nullable = false)
    @JsonIgnoreProperties("maintenanceRecords")
    private Car car;

    @JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss")
    private Date timestamp = new Date(System.currentTimeMillis());
    private long miles = 0L;
    @Column(nullable = false)
    private String maintainer;
    @Column(nullable = false)
    private String actiontaken;
    private String notes = "";

    public MaintenanceRecord() {
    }

    public int getId() {
        return recordid;
    }

    public void setId(int recordid) {
        this.recordid = recordid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getMiles() {
        return miles;
    }

    public void setMiles(long miles) {
        this.miles = miles;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getActiontaken() {
        return actiontaken;
    }

    public void setActiontaken(String actiontaken) {
        this.actiontaken = actiontaken;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car cars) {
        this.car = cars;
    }
}
