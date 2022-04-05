package com.berkanterdogan.lab.springcachelab.service;

import com.berkanterdogan.lab.springcachelab.domain.Car;

import java.util.List;

public interface CarService {

    void deleteAll();

    void deleteById(Long id);

    void saveAll(Iterable<Car> cars);

    Car save(Car car);

    Car update(Car car);

    List<Car> findAll();

    Car findById(Long id);
}
