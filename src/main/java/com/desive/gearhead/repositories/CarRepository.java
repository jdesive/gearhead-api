package com.desive.gearhead.repositories;

import com.desive.gearhead.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

    Car findByVin(long vin);

}
