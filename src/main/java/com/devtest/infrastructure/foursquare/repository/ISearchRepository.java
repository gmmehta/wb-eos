package com.devtest.infrastructure.foursquare.repository;

import java.util.List;

/**
 * Repository to fetch venus near a given place e.g. Holborn
 * using FourSquare
 */
public interface ISearchRepository {

    List<String> search(String place);

}
