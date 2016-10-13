package com.devtest.infrastructure.foursquare.service;

import java.util.List;

/**
 * An json extractor to extract information using JsonPath.
 */
public interface IJsonExtractor {

    /**
     * Given a JSON string extract required information
     *
     * @param json
     * @return extracted values
     */
    List<String> extract(String json);
}
