package com.gerigol.budoka.service;

import com.gerigol.budoka.domain.Location;
import com.gerigol.budoka.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public boolean locationExists(String locationName) {
        return locationRepository.existsByName(locationName);
    }

    public Location getLocationByName(String locationName) {
        if (locationExists(locationName))
            return locationRepository.findByName(locationName);
        throw new RuntimeException(); // TODO: Create proper custom error handling!
    }

    public Location createLocation(Location location) {
        validateLocation(location);
        return locationRepository.save(location);
    }

    private void validateLocation(Location location) {
        checkIfLocationNameExists(location.getName());
        if (location.getName().isBlank() || location.getAddress().isBlank())
            throw new RuntimeException(); // TODO create proper custom error handling!
    }

    private void checkIfLocationNameExists(String name) {
        if (locationExists(name))
            throw new RuntimeException(); // TODO create proper custom error handling!
    }
}
