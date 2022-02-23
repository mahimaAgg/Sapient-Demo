package com.sapient.weather.config;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sapient.weather.model.Weather;

@Component
public class WeatherClient {

	public Weather getWeather(String city) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(
				"https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=d2929e9483efc82c82c32ee7e02d563e&cnt=10",
				Weather.class);

	}

}
