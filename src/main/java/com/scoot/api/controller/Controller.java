package com.scoot.api.controller;

import com.scoot.api.model.DistanceRequest;
import com.scoot.api.service.DistanceCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/distance")
public class Controller {

    private final DistanceCalculationService distanceCalculationService;

    @Autowired
    public Controller(DistanceCalculationService distanceCalculationService) {
        this.distanceCalculationService = distanceCalculationService;
    }

    @PostMapping
    public ResponseEntity<Double> calculateDistance(@RequestBody DistanceRequest
                                                            request, @RequestHeader("X-Api-Key") String apiKey) {
        double distance = distanceCalculationService.calculateDistanceBetweenPoints(request, apiKey);
        return ResponseEntity.ok(distance);
    }
}
