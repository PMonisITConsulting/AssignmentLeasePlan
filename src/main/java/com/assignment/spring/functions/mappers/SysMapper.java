package com.assignment.spring.functions.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.model.Sys;

@Component
@Qualifier("SysMapper")
public class SysMapper implements Function<WeatherResponse, Sys> {

	@Autowired
	public SysMapper() {
	}

	@Override
	public Sys apply(WeatherResponse response) {
		return response.getSys();
	}

}