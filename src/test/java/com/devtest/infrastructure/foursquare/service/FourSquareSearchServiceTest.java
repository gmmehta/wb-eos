package com.devtest.infrastructure.foursquare.service;

import com.devtest.infrastructure.ISearchService;
import com.devtest.infrastructure.foursquare.repository.ISearchRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class FourSquareSearchServiceTest {

    private ISearchService fourSquareSearchService;

    @Mock
    private ISearchRepository fourSquareSearchRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fourSquareSearchService = new FourSquareSearchService(fourSquareSearchRepository);
    }

    @Test
    public void whenGivenPlaceIsNullThenReturnNoVenues() throws Exception {
        String place = null;
        List<String> expectedVenues = Arrays.asList("No venues found");
        assertRecommendedVenuesNearPlace(place, expectedVenues);
    }

    @Test
    public void whenGivenPlaceIsEmptyThenReturnNoVenues() throws Exception {
        String place = "";
        List<String> expectedVenues = Arrays.asList("No venues found");
        assertRecommendedVenuesNearPlace(place, expectedVenues);
    }

    @Test
    public void whenPlaceIsUnknownThenReturnNoVenues() throws Exception {
        String place = "unknown_place";
        List<String> expectedVenues = Arrays.asList("No venues found");
        assertRecommendedVenuesNearPlace(place, expectedVenues);
    }

    @Test
    public void whenPlaceIsKnownThenReturnVenues() throws Exception {
        String place = "holborn";
        List<String> expectedVenues = Arrays.asList("National Gallery", "British Museum", "Somerset House", "Dishoom");
        assertRecommendedVenuesNearPlace(place, expectedVenues);
    }

    private void assertRecommendedVenuesNearPlace(String place, List<String> expectedVenues) throws Exception {
        //given
        given(fourSquareSearchRepository.search(anyString())).willReturn(expectedVenues);

        //when
        List<String> actualVenues = fourSquareSearchService.recommendedVenuesNearBy(place);

        //then
        verify(fourSquareSearchRepository, atLeastOnce()).search(anyString());
        assertThat(actualVenues, is(expectedVenues));
    }
}
