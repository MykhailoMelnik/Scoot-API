package com.scoot.api.service;

import com.scoot.api.model.DistanceRequest;

public interface DistanceCalculationService {
    double calculateDistanceBetweenPoints(DistanceRequest request, String apiKey);
}
