package com.desive.gearhead.repositories;

import com.desive.gearhead.entities.Car;
import com.desive.gearhead.entities.MaintenanceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer>{

    Page<MaintenanceRecord> findByCar(Car car, Pageable pageable);

    Page<MaintenanceRecord> findByTimestamp(Date date, Pageable pageable);
}
