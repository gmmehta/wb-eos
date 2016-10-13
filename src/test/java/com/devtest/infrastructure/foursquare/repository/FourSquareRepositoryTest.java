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
public class FourSquareRepositoryTest {

    @Autowired
    private IFourSquareRepository fourSquareRepository;

    @Test
    public void whenGivenPlaceIsEmpty() throws Exception {
        List<String> venues = fourSquareRepository.explore("");
        assertThat(venues.isEmpty(), is(true));
    }

    @Test
    public void whenGivenPlaceIsUnknown() throws Exception {
        List<String> venues = fourSquareRepository.explore("sdhfsdkjfhsjkdfhsdjhsdkjfh");
        assertThat(venues.isEmpty(), is(true));
    }

    @Test
    public void whenGivenPlaceIsKnown() throws Exception {
        List<String> venues = fourSquareRepository.explore("holborn");
        assertThat(venues.isEmpty(), is(false));
        assertThat(venues, Matchers.hasItems("National Gallery", "British Museum", "Somerset House", "Dishoom"));
    }
}
