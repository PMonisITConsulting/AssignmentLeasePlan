package com.assignment.spring.service.openWeather;

import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.constants.Constants;

@Component
@Qualifier( "GetWeather" )
public class GetWeather implements Function<String, Optional<WeatherResponse>>{
	
	private static final Logger logger = LoggerFactory.getLogger( GetWeather.class );
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Optional<WeatherResponse> apply(String aCity) {
		try {
    		String url = Constants.WEATHER_API_URL.replace("{city}", aCity).replace("{appid}", Constants.APP_ID);
    		
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

            if ( response.getStatusCode().equals( HttpStatus.OK ) ) {
                return Optional.of(response.getBody());
            }

        } catch ( Exception e ) {
            logger.error( e.getMessage(), e );
        }
		
		
		return Optional.empty();
	}
}
