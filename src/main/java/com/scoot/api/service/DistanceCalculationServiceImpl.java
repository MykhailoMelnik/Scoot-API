package com.scoot.api.service;

import com.scoot.api.bucket4j.BucketManager;
import com.scoot.api.entity.ExternalUser;
import com.scoot.api.exceptionHandler.exception.RateLimitExceededException;
import com.scoot.api.model.DistanceRequest;
import com.scoot.api.repository.ExternalUserRepository;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service calculates the distance between two points on Earth and enforces rate limiting for users.
 * It uses the Haversine formula to calculate the distance based on latitude and longitude coordinates.
 * If the rate limit is exceeded, a RateLimitExceededException is thrown.
 */

@Service
public class DistanceCalculationServiceImpl implements DistanceCalculationService {
    private final ExternalUserRepository userRepository;
    private final BucketManager bucketManager;

    @Autowired
    public DistanceCalculationServiceImpl(ExternalUserRepository userRepository, BucketManager bucketManager) {
        this.userRepository = userRepository;
        this.bucketManager = bucketManager;
    }

    @Override
    public double calculateDistanceBetweenPoints(DistanceRequest request, String apiKey) {
        ExternalUser user = userRepository.findByApiKey(apiKey).orElse(null);
        if (user != null) {
            Bucket userBucket = bucketManager.getBucketForApiKey(apiKey);
            if (userBucket.tryConsume(1)) {
                return calculateDistance(request);
            }
        }
        throw new RateLimitExceededException("Rate limit exceeded. Try again later.");
    }

    public double calculateDistance(
            DistanceRequest request) {
        double radius = 6371;
        double dLat = Math.toRadians(request.getLatitude2() - request.getLatitude1());
        double dLon = Math.toRadians(request.getLongitude2() - request.getLongitude1());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(request.getLatitude1())) * Math.cos(Math.toRadians(request.getLatitude2())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c;
    }
}

