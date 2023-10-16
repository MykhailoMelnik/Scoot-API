package com.scoot.api.service;

import com.scoot.api.bucket4j.BucketManagerImpl;
import com.scoot.api.entity.ExternalUser;
import com.scoot.api.model.DistanceRequest;
import com.scoot.api.repository.ExternalUserRepository;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DistanceCalculationServiceImplTest {

    private DistanceCalculationServiceImpl distanceCalculationService;

    @Mock
    private ExternalUserRepository userRepository;

    @Mock
    private BucketManagerImpl bucketManager;

    @BeforeEach
    public void setup() {
        distanceCalculationService = new DistanceCalculationServiceImpl(userRepository, bucketManager);
    }

    @Test
    public void testCalculateDistanceBetweenPoints() {
        DistanceRequest request = new DistanceRequest(
                40.7128, -74.0060,
                34.0522, -118.2437);
        String apiKey = "your_api_key";

        ExternalUser user = new ExternalUser();
        user.setApiKey(apiKey);

        Bucket bucket = Bucket.builder()
                .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
                .build();

        when(userRepository.findByApiKey(apiKey)).thenReturn(java.util.Optional.of(user));
        when(bucketManager.getBucketForApiKey(apiKey)).thenReturn(bucket);

        double distance = distanceCalculationService.calculateDistanceBetweenPoints(request, apiKey);

        assertEquals(3935.74, distance, 0.01);
    }
}
