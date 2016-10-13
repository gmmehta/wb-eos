package com.devtest.infrastructure.foursquare.repository;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Functional test to see if we are getting anything back from FourSquare
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FourSquareSearchRepositoryTest {

    @Autowired
    private ISearchRepository fourSquareSearchRepository;

    @Test
    public void whenGivenPlaceIsNullThenReturnEmptyVenues() throws Exception {
        List<String> venues = fourSquareSearchRepository.search(null);
        assertThat(venues.isEmpty(), is(true));
    }

    @Test
    public void whenGivenPlaceIsEmptyThenReturnEmptyVenues() throws Exception {
        List<String> venues = fourSquareSearchRepository.search("");
        assertThat(venues.isEmpty(), is(true));
    }

    @Test
    public void whenGivenPlaceIsUnknownThenReturnEmptyVenues() throws Exception {
        List<String> venues = fourSquareSearchRepository.search("sdhfsdkjfhsjkdfhsdjhsdkjfh");
        assertThat(venues.isEmpty(), is(true));
    }

    @Test
    public void whenGivenPlaceIsKnownThenReturnVenues() throws Exception {
        List<String> venues = fourSquareSearchRepository.search("holborn");
        assertThat(venues.isEmpty(), is(false));
        assertThat(venues, Matchers.hasItems("National Gallery", "British Museum", "Somerset House", "Dishoom"));
    }
}
