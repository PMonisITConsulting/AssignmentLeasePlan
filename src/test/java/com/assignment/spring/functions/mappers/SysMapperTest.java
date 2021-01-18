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
import com.assignment.spring.model.Sys;

@RunWith(SpringJUnit4ClassRunner.class)
public class SysMapperTest {

	private Function<WeatherResponse, Sys> sysMapper = new SysMapper();

	@Mock
	private WeatherResponse weatherResponse;

	@Mock
	private Sys sys;

	@Before
	public void setUp() {
		Mockito.when(weatherResponse.getSys()).thenReturn(sys);
		Mockito.when(sys.getCountry()).thenReturn("NL");
	}

	@Test
	public void mainMapper() {
		Sys sysMapped = sysMapper.apply(weatherResponse);

		Assert.assertNotNull(sysMapped);
		Assert.assertEquals("NL", sysMapped.getCountry());
	}

}
