package com.HW.joining_pojos.service;

import com.HW.joining_pojos.models.Tour;
import com.HW.joining_pojos.repos.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {
    private final TourRepository tourRepository;

    @Autowired
    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Optional<Tour> getTourById(Long id) {
        return tourRepository.findById(id);
    }

    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public Tour updateTour(Long id, Tour tourDetails) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if (optionalTour.isPresent()) {
            Tour tour = optionalTour.get();
            tour.setArtistName(tourDetails.getArtistName());
            tour.setTourName(tourDetails.getTourName());
            tour.setLocations(tourDetails.getLocations());
            return tourRepository.save(tour);
        }
        return null;
    }

    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }
}