package com.assignment.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.assignment.spring.entity.WeatherEntity;

public interface WeatherRepository extends CrudRepository<WeatherEntity, Integer> {
	
	
}
