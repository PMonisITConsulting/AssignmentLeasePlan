package com.assignment.spring.controller;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.entity.WeatherEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WeatherController {

	private final Function<String, Optional<WeatherResponse>> getWeather;
	private final Function<WeatherResponse, WeatherEntity> weatherMapper;
	private final Function<WeatherEntity, WeatherEntity> addWeather;

	@Autowired
	public WeatherController(@Qualifier("GetWeather") Function<String, Optional<WeatherResponse>> getWeather,
							 @Qualifier("WeatherMapper") Function<WeatherResponse, WeatherEntity> weatherMapper,
							 @Qualifier("AddWeather") Function<WeatherEntity, WeatherEntity> addWeather) {
		this.getWeather = getWeather;
		this.weatherMapper = weatherMapper;
		this.addWeather = addWeather;
	}

	@RequestMapping("/weather")
	public WeatherEntity weather(HttpServletRequest request) {
		Optional<WeatherResponse> response = getWeather.apply(request.getParameter("city"));
		
		if (response.isPresent()) {
			return addWeather.apply(weatherMapper.apply(response.get()));
		}

		return null;
	}
}
