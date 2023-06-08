package com.HW.joining_pojos.controller;
import com.HW.joining_pojos.models.Tour;
import com.HW.joining_pojos.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tours = tourService.getAllTours();
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long id) {
        Optional<Tour> optionalTour = tourService.getTourById(id);
        return optionalTour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour createdTour = tourService.createTour(tour);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTour);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tour) {
        Tour updatedTour = tourService.updateTour(id, tour);
        return updatedTour != null ? ResponseEntity.ok(updatedTour) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }
}
