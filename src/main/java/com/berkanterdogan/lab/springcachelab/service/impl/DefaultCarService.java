package com.berkanterdogan.lab.springcachelab.service.impl;

import com.berkanterdogan.lab.springcachelab.domain.Car;
import com.berkanterdogan.lab.springcachelab.repository.CarRepository;
import com.berkanterdogan.lab.springcachelab.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DefaultCarService implements CarService {

    private final CarRepository carRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "cars", allEntries=true),
            @CacheEvict(value = "car", allEntries=true),
    })
    @Transactional
    public void deleteAll() {
        carRepository.deleteAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "cars", allEntries = true),
            @CacheEvict(value = "car", key = "#id"),
    })
    @Transactional
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "cars", allEntries = true)
    @Transactional
    public void saveAll(Iterable<Car> cars) {
        carRepository.saveAll(cars);
    }

    @Override
    @CacheEvict(value = "cars", allEntries = true)
    @Transactional
    public Car save(Car car) {
        if (car == null || car.getId() != null) {
            throw new IllegalArgumentException("A car must have an id");
        }
        return carRepository.save(car);
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "car", key = "#car.id")
            },
            evict = {
                    @CacheEvict(value = "cars", allEntries = true)
            }
    )
    @Transactional
    public Car update(Car car) {
        if (!carRepository.existsById(car.getId())) {
            throw new IllegalArgumentException("A car must have an id");
        }

        return carRepository.save(car);
    }

    @SneakyThrows
    @Override
    @Cacheable(cacheNames = "cars")
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "car", key = "#id", unless = "#result == null")
    @Transactional(readOnly = true)
    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
