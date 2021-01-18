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
import com.assignment.spring.model.Main;

@RunWith(SpringJUnit4ClassRunner.class)
public class MainMapperTest {

	private Function<WeatherResponse, Main> mainMapper = new MainMapper();

	@Mock
	private WeatherResponse weatherResponse;

	@Mock
	private Main main;

	@Before
	public void setUp() {
		Mockito.when(weatherResponse.getMain()).thenReturn(main);
		Mockito.when(main.getPressure()).thenReturn(100);
	}

	@Test
	public void mainMapper() {
		Main mainMapped = mainMapper.apply(weatherResponse);

		Assert.assertNotNull(mainMapped);
		Assert.assertEquals(Integer.valueOf(100), mainMapped.getPressure());
	}

}
