package com.scoot.api.controller;

import com.scoot.api.model.DistanceRequest;
import com.scoot.api.service.DistanceCalculationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ControllerTest {

    @Mock
    private DistanceCalculationService distanceCalculationService;

    @Test
    public void testCalculateDistance() {
        Controller controller = new Controller(distanceCalculationService);

        DistanceRequest request = new DistanceRequest(40.7128, -74.0060,
                34.0522, -118.2437);
        String apiKey = "your_api_key";
        double expectedDistance = 3934.42;

        when(distanceCalculationService.calculateDistanceBetweenPoints(request, apiKey))
                .thenReturn(expectedDistance);

        ResponseEntity<Double> response = controller.calculateDistance(request, apiKey);

        Double responseBody = response.getBody();

        assertNotNull(responseBody);
        assertEquals(expectedDistance, responseBody, 0.01);
    }
}
