package com.devtest.infrastructure.foursquare.repository;

import java.util.List;

public interface IFourSquareRepository {

    List<String> explore(String place);

}
