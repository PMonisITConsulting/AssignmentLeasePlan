package com.assignment.spring.functions.mappers;

import java.util.function.Function;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.model.Main;
import com.assignment.spring.model.Sys;
import com.assignment.spring.model.Weather;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherMapperTest {

	private Function<WeatherResponse, WeatherEntity> weatherMapper;
	
	@Mock
	private Function<WeatherResponse, Sys> sysMapper;

	@Mock
	private Function<WeatherResponse, Main> mainMapper;

	@Mock
	private WeatherResponse weatherResponse;

	@Mock
	private Weather weather;

	@Mock
	private Sys sys;

	@Mock
	private Main main;

	@Before
	public void setUp() {
		
		weatherMapper = new WeatherMapper(sysMapper, mainMapper);
		
		Mockito.when(weatherResponse.getName()).thenReturn("Amsterdam");

		Mockito.when(sysMapper.apply(weatherResponse)).thenReturn(sys);
		Mockito.when(sys.getCountry()).thenReturn("NL");

		Mockito.when(mainMapper.apply(weatherResponse)).thenReturn(main);
		Mockito.when(main.getTemp()).thenReturn(Double.valueOf(100));
	}

	@Test
	public void mainMapper() {
		WeatherEntity weatherMapped = weatherMapper.apply(weatherResponse);

		Assert.assertNotNull(weatherMapped);
		Assert.assertEquals(Double.valueOf(100), weatherMapped.getTemperature());
		Assert.assertEquals("NL", weatherMapped.getCountry());
		Assert.assertEquals("Amsterdam", weatherMapped.getCity());
	}

}
