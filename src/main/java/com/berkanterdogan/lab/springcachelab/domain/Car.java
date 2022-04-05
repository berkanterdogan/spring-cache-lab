package com.berkanterdogan.lab.springcachelab.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {

    public enum CarTypeEnum {
        SEDAN,
        HATCHBACK,
        SUV
    }

    public enum CarColourEnum {
        WHITE,
        BLACK,
        RED,
        GREEN,
        BLUE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private CarTypeEnum type;

    @Enumerated(value = EnumType.STRING)
    private CarColourEnum colour;
}
