package com.HW.joining_pojos.service;

import com.HW.joining_pojos.models.Location;
import com.HW.joining_pojos.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location locationDetails) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setCityName(locationDetails.getCityName());
            location.setDate(locationDetails.getDate());
            location.setTour(locationDetails.getTour());
            return locationRepository.save(location);
        }
        return null;
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
