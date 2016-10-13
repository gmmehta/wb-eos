package com.devtest.infrastructure.foursquare.repository;

import com.devtest.infrastructure.foursquare.service.IJsonExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FourSquareSearchRepository implements ISearchRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FourSquareSearchRepository.class);

    private final String fourSqUri;
    private final RestTemplate restTemplate;
    private final IJsonExtractor iJsonExtractor;

    public FourSquareSearchRepository(String fourSqUri,
                                      RestTemplate restTemplate,
                                      IJsonExtractor iJsonExtractor) {
        this.fourSqUri = fourSqUri;
        this.restTemplate = restTemplate;
        this.iJsonExtractor = iJsonExtractor;
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
