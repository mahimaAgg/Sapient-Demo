
package com.sapient.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.weather.config.WeatherClient;
import com.sapient.weather.exception.InvalidWeatherDataException;
import com.sapient.weather.model.Weather;
import com.sapient.weather.model.WeatherForecast;
import com.sapient.weather.model.WeatherObjectList;
import com.sapient.weather.utility.Constants;

@Service
public class WeatherServiceImpl implements WeatherService {

	private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	private WeatherClient weatherClient;

	@Override
	public List<WeatherForecast> getCityWeather(String city) {

		Weather cityWeather = weatherClient.getWeather(city);

		List<WeatherForecast> dayWeatherList = new ArrayList<>();
		WeatherForecast weatherForecast = null;
		List<WeatherObjectList> threeDaysCityWeather = cityWeather.getList().subList(0, 3);
		if (threeDaysCityWeather != null && cityWeather.getCity().getName().equalsIgnoreCase(city)) {
			for (WeatherObjectList weatherObjectList : threeDaysCityWeather) {

				weatherForecast = new WeatherForecast();

				if (weatherObjectList.getMain() != null) {

					weatherForecast.setHigh(weatherObjectList.getMain().getTemp_max());
					weatherForecast.setLow(weatherObjectList.getMain().getTemp_min());

					float temperatureInCelsius = convertTempToCelsius(weatherObjectList.getMain().getTemp());

					if (temperatureInCelsius > 40.0) {
						weatherForecast.setMessage(Constants.USE_SUNSCREEN_LOTION);
					} else if (weatherObjectList.getWeather().get(0).getMain().contains(Constants.RAIN)) {
						weatherForecast.setMessage(Constants.CARRY_UMBRELLA);
					} else {
						weatherForecast.setMessage(weatherObjectList.getWeather().get(0).getMain());
					}

				} else {
					LOG.error("Temperature data is missing for the city");
					throw new InvalidWeatherDataException(Constants.EXTERNAL_API_DATA_INCORRECT);
				}

				dayWeatherList.add(weatherForecast);
			}
		} else {
			LOG.error("Temperature data is missing for the city");
			throw new InvalidWeatherDataException(Constants.EXTERNAL_API_DATA_INCORRECT);
		}
		return dayWeatherList;
	}

	public float convertTempToCelsius(float temp) {
		return temp - 273.15F;
	}
}
