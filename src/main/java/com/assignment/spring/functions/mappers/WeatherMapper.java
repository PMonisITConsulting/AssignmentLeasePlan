package com.assignment.spring.functions.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.model.Main;
import com.assignment.spring.model.Sys;

@Component
@Qualifier("WeatherMapper")
public class WeatherMapper implements Function<WeatherResponse, WeatherEntity> {

	private final Function<WeatherResponse, Sys> sysMapper;
	private final Function<WeatherResponse, Main> mainMapper;

	@Autowired
	public WeatherMapper(@Qualifier("SysMapper") Function<WeatherResponse, Sys> sysMapper,
						 @Qualifier("MainMapper") Function<WeatherResponse, Main> mainMapper) {
		this.sysMapper = sysMapper;
		this.mainMapper = mainMapper;
	}

	@Override
	public WeatherEntity apply(WeatherResponse response) {
		WeatherEntity entity = new WeatherEntity();
		entity.setCity(response.getName());
		entity.setCountry(sysMapper.apply(response).getCountry());
		entity.setTemperature(mainMapper.apply(response).getTemp());

		return entity;
	}

}