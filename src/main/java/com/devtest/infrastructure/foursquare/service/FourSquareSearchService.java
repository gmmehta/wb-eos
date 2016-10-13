package com.devtest.infrastructure.foursquare.service;

import com.devtest.infrastructure.ISearchService;
import com.devtest.infrastructure.foursquare.repository.ISearchRepository;

import java.util.List;

public class FourSquareSearchService implements ISearchService {

    private final ISearchRepository fourSquareRepository;

    public FourSquareSearchService(ISearchRepository fourSquareRepository) {
        this.fourSquareRepository = fourSquareRepository;
    }

    @Override
    public List<String> recommendedVenuesNearBy(String place) {
        List<String> venues = fourSquareRepository.search(place);
        if (venues.isEmpty()) {
            venues.add(NO_VENUES_FOUND);
        }
        return venues;
    }
}
