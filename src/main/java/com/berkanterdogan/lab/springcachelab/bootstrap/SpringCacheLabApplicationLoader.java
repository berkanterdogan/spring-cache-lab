package com.berkanterdogan.lab.springcachelab.bootstrap;

import com.berkanterdogan.lab.springcachelab.domain.Car;
import com.berkanterdogan.lab.springcachelab.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SpringCacheLabApplicationLoader implements CommandLineRunner {

    public static final String RESULT_FOR_LOG = "RESULT: {}";
    
    private final CarService carService;

    @Override
    public void run(String... args) throws Exception {
        carService.deleteAll();

        Car.CarBuilder carBuilder = Car.builder();
        Car mercedes = carBuilder.name("Mercedes").colour(Car.CarColourEnum.BLACK).type(Car.CarTypeEnum.SEDAN).build();
        Car bmw = carBuilder.name("BMW").colour(Car.CarColourEnum.RED).type(Car.CarTypeEnum.HATCHBACK).build();
        Car opel = carBuilder.name("Opel").colour(Car.CarColourEnum.WHITE).type(Car.CarTypeEnum.SUV).build();
        Car ford = carBuilder.name("Ford").colour(Car.CarColourEnum.BLUE).type(Car.CarTypeEnum.HATCHBACK).build();

        log.info("1 - Saving cars... - carService.saveAll(List.of(mercedes, bmw, opel, ford))");
        carService.saveAll(List.of(mercedes, bmw, opel, ford));

        log.info("2 - Fetching cars from DB... - carService.findAll()");
        List<Car> cars = carService.findAll();
        log.info(RESULT_FOR_LOG, cars);

        log.info("3 - Fetching cars from cache... - carService.findAll()");
        cars = carService.findAll();
        log.info(RESULT_FOR_LOG, cars);

        Long mercedesId = mercedes.getId();
        log.info("4 - Fetching car by id from DB... - carService.findById(mercedesId)");
        mercedes = carService.findById(mercedesId);
        log.info(RESULT_FOR_LOG, mercedes);

        log.info("5 - Fetching car by id from cache... - carService.findById(mercedesId)");
        mercedes = carService.findById(mercedesId);
        log.info(RESULT_FOR_LOG, mercedes);

        log.info("6 - Updating car and its cache value; delete cache values of cars... - carService.update(mercedes)");
        mercedes.setColour(Car.CarColourEnum.GREEN);
        mercedes = carService.update(mercedes);
        log.info(RESULT_FOR_LOG, mercedes);

        log.info("7 - Fetching car by id from cache(updated)... - carService.findById(mercedesId)");
        mercedes = carService.findById(mercedesId);
        log.info(RESULT_FOR_LOG, mercedes);

        log.info("8 - Deleting car and its cache by id - carService.deleteById(mercedesId)");
        carService.deleteById(mercedesId);

        log.info("9 - Fetching car(deleted) by id from DB... - carService.findById(mercedesId)");
        mercedes = carService.findById(mercedesId);
        log.info(RESULT_FOR_LOG, mercedes);

        log.info("10 - Fetching cars from DB... - carService.findAll()");
        cars = carService.findAll();
        log.info(RESULT_FOR_LOG, cars);

        log.info("11 - Saving deleted car again... - carService.save(mercedes)");
        mercedes = carBuilder.name("Mercedes").colour(Car.CarColourEnum.BLACK).type(Car.CarTypeEnum.SEDAN).build();
        mercedes = carService.save(mercedes);
        log.info(RESULT_FOR_LOG, mercedes);

        mercedesId = mercedes.getId();
        log.info("12 - Fetching car by id from DB... - carService.findById(mercedesId)");
        mercedes = carService.findById(mercedesId);
        log.info(RESULT_FOR_LOG, mercedes);

        log.info("13 - Fetching car by id from cache... - carService.findById(mercedesId)");
        mercedes = carService.findById(mercedesId);
        log.info(RESULT_FOR_LOG, mercedes);

        log.info("14 - Deleting all cars and its cache... - carService.deleteAll()");
        carService.deleteAll();

        log.info("15 - Fetching cars from DB... - carService.findAll()");
        cars = carService.findAll();
        log.info(RESULT_FOR_LOG, cars);

        log.info("16 - Fetching car by id from DB... - carService.findById(mercedesId)");
        mercedes = carService.findById(mercedesId);
        log.info(RESULT_FOR_LOG, mercedes);
    }
}
