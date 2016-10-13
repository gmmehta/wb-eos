package com.devtest.infrastructure.foursquare.repository;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FourSquareRepository implements IFourSquareRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FourSquareRepository.class);

    private final String fourSqUri;
    private final RestTemplate restTemplate;

    public FourSquareRepository(String fourSqUri, RestTemplate restTemplate) {
        this.fourSqUri = fourSqUri;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> explore(String place) {
        List<String> venues = new ArrayList<>();
        try {
            String json = restTemplate.getForObject(fourSqUri, String.class, place);
            venues = JsonPath.read(json, "$.response.groups[*].items[*].venue.name");
        } catch (RestClientException rce) {
            LOG.error("Couldn't find venues near: " + place, rce);
        }
        return venues;
    }
}
