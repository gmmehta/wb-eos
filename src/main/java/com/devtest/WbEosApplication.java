package com.devtest;

import com.devtest.infrastructure.ISearchService;
import com.devtest.infrastructure.foursquare.repository.FourSquareSearchRepository;
import com.devtest.infrastructure.foursquare.repository.ISearchRepository;
import com.devtest.infrastructure.foursquare.service.FourSquareSearchService;
import com.devtest.infrastructure.foursquare.service.FourSquareVenuesExtractor;
import com.devtest.infrastructure.foursquare.service.IJsonExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
    public ISearchRepository fourSquareSearchRepository(@Value("${fourSq.uri}") String fourSqUri,
                                                        RestTemplate restTemplate,
                                                        IJsonExtractor fourSquareVenuesExtractor) {
        return new FourSquareSearchRepository(fourSqUri, restTemplate, fourSquareVenuesExtractor);
    }

    @Bean
    public ISearchService fourSquareSearchService(ISearchRepository fourSquareRepository) {
        return new FourSquareSearchService(fourSquareRepository);
    }

}
