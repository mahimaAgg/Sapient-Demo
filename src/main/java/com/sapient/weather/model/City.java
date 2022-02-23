package com.sapient.weather.model;

import lombok.Data;

@Data
public class City {

    private float id;
    private String name;
    private Coord coord;
    private String country;
}
