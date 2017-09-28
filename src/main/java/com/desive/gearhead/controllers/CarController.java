package com.desive.gearhead.controllers;

import com.desive.gearhead.entities.Car;
import com.desive.gearhead.entities.MaintenanceRecord;
import com.desive.gearhead.exceptions.CarNotFoundException;
import com.desive.gearhead.repositories.CarRepository;
import com.desive.gearhead.repositories.MaintenanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    /*
    List all cars
     */
    @RequestMapping(method = RequestMethod.GET, value = "/cars/all", params = {"page", "size"})
    private Page<Car> listCars(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size){
        return this.carRepository.findAll(new PageRequest(page, size));
    }

    /*
    Get a specific car by id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/cars/id/{id}")
    private Car getCarById(@PathVariable(name = "id") int id){
        Car car = this.carRepository.findOne(id);
        if(car == null)
            throw new CarNotFoundException(id);

        return car;
    }

    /*
    Get a specific car by id
    */
    @RequestMapping(method = RequestMethod.GET, value = "/cars/vin/{vin}")
    private Car getCarByVin(@PathVariable(name = "vin") long vin){
        Car car = this.carRepository.findByVin(vin);
        if(car == null)
            throw new CarNotFoundException(vin);

        return car;
    }

    /*
    Save a new car
     */
    @RequestMapping(method = RequestMethod.POST, value = "/cars")
    private Car addCar(@RequestBody Car car){
        return this.carRepository.save(car);
    }

    /*
    Save a new maintenance record
     */
    @RequestMapping(method = RequestMethod.POST, value = "/cars/{id}/maintenance")
    private MaintenanceRecord addMaintenanceRecord(@PathVariable int id, @RequestBody MaintenanceRecord record){
        Car car = this.carRepository.findOne(id);
        if(car == null)
            throw new CarNotFoundException(id);
        record.setCar(car);
        return this.maintenanceRecordRepository.save(record);
    }

    /*
    Get all maintenance records for a car by id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/cars/{id}/maintenance/all", params = {"page", "size"})
    private Page<MaintenanceRecord> getMaintenanceRecordsById(@PathVariable int id, @RequestParam(name = "page", defaultValue = "0")
            int page, @RequestParam(name = "size", defaultValue = "20") int size){

        Car car = this.carRepository.findOne(id);
        if(car == null)
            throw new CarNotFoundException(id);
        return this.maintenanceRecordRepository.findByCar(car, new PageRequest(page, size));
    }

}
