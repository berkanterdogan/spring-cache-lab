package com.berkanterdogan.lab.springcachelab.repository;

import com.berkanterdogan.lab.springcachelab.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
