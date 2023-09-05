package com.gerigol.budoka.utility;

import com.gerigol.budoka.controller.dto.LocationDTO;
import com.gerigol.budoka.domain.Location;

public class LocationMapper {
    public static Location toLocation(LocationDTO locationDTO) {
        return new Location(locationDTO.name(),
                locationDTO.city(),
                locationDTO.address());
    }
}
