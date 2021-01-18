package com.assignment.spring.functions;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.service.openWeather.GetWeather;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetWeatherTest {

	@MockBean
	private RestTemplate restTemplate;

	@InjectMocks
	private GetWeather getWeather;

	@Mock
	private ResponseEntity<Object> response;

	@Mock
	private WeatherResponse weatherResponse;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(response);
		Mockito.when(response.getBody()).thenReturn(weatherResponse);
		Mockito.when(weatherResponse.getName()).thenReturn("aName");
	}

	@Test
	public void getWeather() {
		Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.OK);
		Optional<WeatherResponse> weatherResponse = this.getWeather.apply("Amsterdam");

		Assert.assertFalse(weatherResponse.isEmpty());
		Assert.assertEquals("aName", weatherResponse.get().getName());
	}

	@Test
	public void getWeatherNull() {
		Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.BAD_GATEWAY);
		Optional<WeatherResponse> weatherResponse = this.getWeather.apply("Amsterdam");

		Assert.assertTrue(weatherResponse.isEmpty());
	}
}
