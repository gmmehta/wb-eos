package com.devtest.infrastructure;

import java.util.List;

public interface ISearchService {

    String NO_VENUES_FOUND = "No venues found";

    List<String> recommendedVenuesNearBy(String place);
}
