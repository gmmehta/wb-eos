package com.devtest;

import com.devtest.infrastructure.foursquare.repository.FourSquareRepository;
import com.devtest.infrastructure.foursquare.repository.IFourSquareRepository;
import com.devtest.infrastructure.foursquare.service.FourSquareVenuesExtractor;
import com.devtest.infrastructure.foursquare.service.IJsonExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WbEosApplication {

    public static void main(String[] args) {
        SpringApplication.run(WbEosApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public IJsonExtractor fourSquareVenuesExtractor() {
        return new FourSquareVenuesExtractor();
    }

    @Bean
    public IFourSquareRepository fourSquareRepository(@Value("${fourSq.uri}") String fourSqUri,
                                                      RestTemplate restTemplate,
                                                      IJsonExtractor fourSquareVenuesExtractor) {
        return new FourSquareRepository(fourSqUri, restTemplate, fourSquareVenuesExtractor);
    }

}
