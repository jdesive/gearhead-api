package com.desive.gearhead.controllers;

import com.desive.gearhead.entities.MaintenanceRecord;
import com.desive.gearhead.repositories.MaintenanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MaintenanceRecordController {

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/maintenance/all", params = {"page", "size"})
    private Page<MaintenanceRecord> listMaintenanceRecords(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "20") int size){
        return this.maintenanceRecordRepository.findAll(new PageRequest(page, size));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/maintenance/{date}")
    private Page<MaintenanceRecord> listMaintenanceRecordsByDate(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "20") int size,
                                                           @PathVariable(name = "date") Date date){
        return this.maintenanceRecordRepository.findByTimestamp(date, new PageRequest(page, size));
    }

}
