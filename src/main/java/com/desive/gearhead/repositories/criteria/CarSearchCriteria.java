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

import com.desive.gearhead.entities.Car;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;

/*
 Created by Jack DeSive on 10/2/2017 at 9:26 PM
*/
// ... excluseNulls = true PLEASE
public class CarSearchCriteria {

    private Integer id;
    private String make, model, color, vin, plateNumber, dotNumber, oilType,
            oilFilterModel, airFilterModel, cabinFilterModel, batteryModel;
    private Boolean dotRegistered;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDotNumber() {
        return dotNumber;
    }

    public void setDotNumber(String dotNumber) {
        this.dotNumber = dotNumber;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
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

    public Boolean isDotRegistered() {
        return dotRegistered;
    }

    public void setDotRegistered(Boolean dotRegistered) {
        this.dotRegistered = dotRegistered;
    }

    public boolean isEmpty(){
        if(id != null || make != null || model != null || color != null || vin != null || plateNumber != null ||
                dotNumber != null || oilType != null || oilFilterModel != null || airFilterModel != null ||
                cabinFilterModel != null || batteryModel != null || dotRegistered != null || make != null || make != null)
            return false;
        return true;
    }

    public static Specification<Car> withId(Integer id){
        if(id != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("carsid"), id)));
        }
        return null;
    }

    public static Specification<Car> withMake(String make){
        if(make != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("make"), make)));
        }
        return null;
    }

    public static Specification<Car> withModel(String model){
        if(model != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("model"), model)));
        }
        return null;
    }

    public static Specification<Car> withColor(String color){
        if(color != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("color"), color)));
        }
        return null;
    }

    public static Specification<Car> withVin(String vin){
        if(vin != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("vin"), vin)));
        }
        return null;
    }

    public static Specification<Car> withPlateNumber(String plateNumber){
        if(plateNumber != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("plateNumber"), plateNumber)));
        }
        return null;
    }

    public static Specification<Car> withDotNumber(String dotNumber){
        if(dotNumber != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("dotNumber"), dotNumber)));
        }
        return null;
    }

    public static Specification<Car> withOilType(String oilType){
        if(oilType != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("oilType"), oilType)));
        }
        return null;
    }

    public static Specification<Car> withOilFilterModel(String oilFilterModel){
        if(oilFilterModel != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("oilFilterModel"), oilFilterModel)));
        }
        return null;
    }

    public static Specification<Car> withAirFilterModel(String airFilterModel){
        if(airFilterModel != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("airFilterModel"), airFilterModel)));
        }
        return null;
    }

    public static Specification<Car> withCabinFilterModel(String cabinFilterModel){
        if(cabinFilterModel != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("cabinFilterModel"), cabinFilterModel)));
        }
        return null;
    }

    public static Specification<Car> withBatteryModel(String batteryModel){
        if(batteryModel != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("batteryModel"), batteryModel)));
        }
        return null;
    }

    public static Specification<Car> withDotRegistered(Boolean registered){
        if(registered != null){
            return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("dotRegistered"), registered)));
        }
        return null;
    }

    // Lombok hasn't added excludeNulls to @ToString... My answer
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
