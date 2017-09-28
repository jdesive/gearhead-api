package com.desive.gearhead.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    private Car car;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date timestamp;
    private String oldValue;
    private String newValue;
    private String fieldname;
    private String notes;

    public MaintenanceRecord() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getId() {
        return recordid;
    }

    public void setId(int id) {
        this.recordid = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car cars) {
        this.car = cars;
    }
}