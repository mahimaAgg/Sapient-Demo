package com.sapient.weather.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.weather.exception.ResourceNotFoundException;
import com.sapient.weather.model.WeatherForecast;
import com.sapient.weather.service.WeatherService;
import com.sapient.weather.utility.Constants;


@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

	private final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@Autowired
	private WeatherService weatherService;

	/**
	 * 
	 * @param city
	 * @return
	 */
	@GetMapping("/{city}")
	public ResponseEntity<List<WeatherForecast>> getWeatherByCity(@PathVariable String city) {
		logger.debug("Input City {}", city);

		if (StringUtils.isEmpty(city)) {
			logger.error("Error occured input is either empty or null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			return new ResponseEntity<List<WeatherForecast>>(weatherService.getCityWeather(city), HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(Constants.CITY_WEATHER_NOT_FOUND + city);
		} 
	}

}
