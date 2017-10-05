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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Table(name = "cars")
@JsonPropertyOrder({"id", "plateNumber", "make", "model", "color", "vin"}) // Just for my viewing in Restlet client during dev
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carsid;
    @Column(nullable = false)
    private String make = "";
    @Column(nullable = false)
    private String model = "";
    private String color = "";
    @Column(nullable = false)
    private String vin = "";
    private String oilType = "";
    private double oilCapacity = 0.0;
    private double coolantCapacity = 0.0;
    private String oilFilterModel = "";
    private String airFilterModel = "";
    private String cabinFilterModel = "";
    private String batteryModel = "";
    private String plateNumber = "";
    private boolean dotRegistered = false;
    private String dotNumber = "";

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    @JsonIgnoreProperties("car")
    private Set<CarNote> notes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    @JsonIgnoreProperties("car")
    private Set<MaintenanceRecord> maintenanceRecords = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cars_drivers", joinColumns = @JoinColumn(name = "cars_id"),
            inverseJoinColumns = @JoinColumn(name = "drivers_id"))
    private Set<Driver> drivers = new HashSet<>();

    public Car() {
    }

    public int getId() {
        return carsid;
    }

    public void setId(int id) {
        this.carsid = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public double getOilCapacity() {
        return oilCapacity;
    }

    public void setOilCapacity(double oilCapacity) {
        this.oilCapacity = oilCapacity;
    }

    public double getCoolantCapacity() {
        return coolantCapacity;
    }

    public void setCoolantCapacity(double coolantCapacity) {
        this.coolantCapacity = coolantCapacity;
    }

    public String getOilFilterModel() {
        return oilFilterModel;
    }

    public void setOilFilterModel(String oilFilterModel) {
        this.oilFilterModel = oilFilterModel;
    }

    public String getAirFilterModel() {
        return airFilterModel;
    }

    public void setAirFilterModel(String airFilterModel) {
        this.airFilterModel = airFilterModel;
    }

    public String getCabinFilterModel() {
        return cabinFilterModel;
    }

    public void setCabinFilterModel(String cabinFilterModel) {
        this.cabinFilterModel = cabinFilterModel;
    }

    public String getBatteryModel() {
        return batteryModel;
    }

    public void setBatteryModel(String batteryModel) {
        this.batteryModel = batteryModel;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public boolean isDotRegistered() {
        return dotRegistered;
    }

    public void setDotRegistered(boolean dotRegistered) {
        this.dotRegistered = dotRegistered;
    }

    public String getDotNumber() {
        return dotNumber;
    }

    public void setDotNumber(String dotNumber) {
        this.dotNumber = dotNumber;
    }

    public Set<CarNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<CarNote> notes) {
        this.notes = notes;
    }

    public Set<MaintenanceRecord> getMaintenanceRecords() {
        return maintenanceRecords;
    }

    public void setMaintenanceRecords(Set<MaintenanceRecord> maintenanceRecords) {
        this.maintenanceRecords = maintenanceRecords;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }
}
