package com.assignment.spring.functions.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.model.Main;

@Component
@Qualifier("MainMapper")
public class MainMapper implements Function<WeatherResponse, Main> {

	@Override
	public Main apply(WeatherResponse response) {
		return response.getMain();
	}

}