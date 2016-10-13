package com.devtest.infrastructure.foursquare.service;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * FourSquareVenuesExtractor - for now simply extract the "name" of the venue.
 * You could have it extract more information and map it to your internal Venue
 * object.
 */
public class FourSquareVenuesExtractor implements IJsonExtractor {

    @Override
    public List<String> extract(String json) {
        List<String> venues = new ArrayList<>();

        if (StringUtils.isBlank(json)) {
            return venues;
        }

        return JsonPath.read(json, "$.response.groups[*].items[*].venue.name");
    }
}
