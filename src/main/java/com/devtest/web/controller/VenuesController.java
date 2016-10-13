package com.devtest.web.controller;

import com.devtest.infrastructure.ISearchService;
import com.devtest.web.domain.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.devtest.util.Consts.ERROR;
import static com.devtest.util.Consts.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class VenuesController {

    private final ISearchService searchService;

    @Autowired
    public VenuesController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/api/venues/nearby/{place}", method = GET)
    public ResponseEntity<APIResponse> recommendedVenuesNearBy(@PathVariable("place") String place) {
        APIResponse response = new APIResponse();
        response.setStatus(OK);

        try {
            List<String> venues = searchService.recommendedVenuesNearBy(place);
            response.add("venues", venues);
        } catch (Exception e) {
            response.setStatus(ERROR);
            response.setError("Oops somethings gone wrong!");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
