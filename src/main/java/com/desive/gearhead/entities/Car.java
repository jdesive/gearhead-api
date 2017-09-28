package com.desive.gearhead.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carsid;
    private String make;
    private String model;
    private String color;
    private long vin;
    private String oilType;
    private double oilCapacity;
    private double coolantCapacity;
    private String oilFilterModel;
    private String airFilterModel;
    private String cabinFilterModel;
    private String batteryModel;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    private List<MaintenanceRecord> maintenanceRecords;

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

    public long getVin() {
        return vin;
    }

    public void setVin(long vin) {
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

    @JsonIgnore
    @JsonProperty(value = "maintenanceRecords")
    public List<MaintenanceRecord> getMaintenanceRecords() {
        return maintenanceRecords;
    }

    public void setMaintenanceRecords(List<MaintenanceRecord> maintenanceRecords) {
        this.maintenanceRecords = maintenanceRecords;
    }
}
