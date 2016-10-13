package com.devtest.infrastructure.foursquare.repository;

import com.devtest.infrastructure.foursquare.service.IJsonExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SearchRepository implements ISearchRepository {

    private static final Logger LOG = LoggerFactory.getLogger(SearchRepository.class);

    private final String fourSqUri;
    private final RestTemplate restTemplate;
    private final IJsonExtractor iJsonExtractor;

    public SearchRepository(String fourSqUri,
                            RestTemplate restTemplate,
                            IJsonExtractor fourSquareVenuesExtractor) {
        this.fourSqUri = fourSqUri;
        this.restTemplate = restTemplate;
        this.iJsonExtractor = fourSquareVenuesExtractor;
    }

    @Override
    public List<String> search(String place) {
        List<String> venues = new ArrayList<>();
        try {
            String json = restTemplate.getForObject(fourSqUri, String.class, place);
            venues = iJsonExtractor.extract(json);
        } catch (RestClientException rce) {
            LOG.error(">> Couldn't find venues near: " + place, rce);
        }
        return venues;
    }
}
