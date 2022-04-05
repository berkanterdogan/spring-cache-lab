# SPRING CACHE LAB

This project has some case studies on Spring Cache.

- In `DefaultCarService` class, you can find annotations about Spring Cache. 
- `SpringCacheLabApplicationLoader` class implements `CommandLineRunner` class. This class has my scenario for tracking the Spring Cache structure.
- `CacheConfig` class has a bean definition of `CacheManager` class.

## Requirements

- Java 17

## How to Run

- You can run the application with the maven spring-boot plugin:
```
mvn spring-boot:run
```
**_P.S._**: Configurations of the project are in **application.yml** file. You may want to change them.
If you want to change them, you can run the application with giving them to the command of maven spring-boot plugin dynamically.
See details of running an application with maven: https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#run
