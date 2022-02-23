package com.sapient.weather.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Weather {

    private String cod;
    private float message;
    private float cnt;
    private List<WeatherObjectList> list = new ArrayList<WeatherObjectList>();
    private City city;

    
}
