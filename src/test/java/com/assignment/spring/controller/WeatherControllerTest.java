package com.assignment.spring.controller;

import java.util.Optional;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.entity.WeatherEntity;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherControllerTest {

	private WeatherController controller;
	
	@Mock
	private Function<WeatherResponse, WeatherEntity> weatherMapper;

	@Mock
	private Function<String, Optional<WeatherResponse>> getWeather;
	
	@Mock
	private Function<WeatherEntity, WeatherEntity> addWeather;

	@Mock
	private HttpServletRequest request;

	@Mock
	private WeatherResponse response;
	
	@Mock
	private WeatherEntity weatherEntity;

	@Before
	public void setUp() {	
		controller = new WeatherController(getWeather, weatherMapper, addWeather);
		
		Mockito.when(request.getParameter("city")).thenReturn("Amsterdam");
		
		
		
		this.setEntity();
		Mockito.when(weatherMapper.apply(response)).thenReturn(weatherEntity);
		
		Mockito.when(addWeather.apply(weatherEntity)).thenReturn(weatherEntity);
		
	}
	
	private void setEntity() {
		Mockito.when(weatherEntity.getCity()).thenReturn("Amsterdam");
		Mockito.when(weatherEntity.getCountry()).thenReturn("NL");
		Mockito.when(weatherEntity.getTemperature()).thenReturn(275.14);
	}

	@Test
	public void weather() {
		Mockito.when(getWeather.apply(Mockito.anyString())).thenReturn(Optional.of(response));
		
		WeatherEntity weatherEntity = this.controller.weather(request);

		Assert.assertNotNull(weatherEntity);
		Assert.assertEquals("Amsterdam", weatherEntity.getCity());
		Assert.assertEquals("NL", weatherEntity.getCountry());
		Assert.assertEquals(Double.valueOf(275.14), weatherEntity.getTemperature());
	}
	
	@Test
	public void weatherWithoutResponse() {
		Mockito.when(getWeather.apply(Mockito.anyString())).thenReturn(Optional.empty());
		
		WeatherEntity weatherEntity = this.controller.weather(request);

		Assert.assertNull(weatherEntity);
	}

}
