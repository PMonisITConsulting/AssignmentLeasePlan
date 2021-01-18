package com.assignment.spring.functions;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;

@Component
@Qualifier("AddWeather")
public class AddWeather implements Function<WeatherEntity, WeatherEntity> {

	@Autowired
	private WeatherRepository weatherRepository;

	@Override
	public WeatherEntity apply(WeatherEntity aMappedEntity) {
		return weatherRepository.save(aMappedEntity);
	}
}
