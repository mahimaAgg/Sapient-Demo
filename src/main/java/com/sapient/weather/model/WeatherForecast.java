package com.sapient.weather.model;

import lombok.Data;

@Data
public class WeatherForecast {
    private float high;
    private float low;
    private String message;

}
