package com.sapient.weather.model;

import java.util.List;

import lombok.Data;

@Data
public class WeatherObjectList {

    private float dt;
    private Main main;
    private List<WeatherDetail> weather;
    private Clouds clouds;
    private Wind wind;
    private Sys sys;
    private String dt_txt;


}
