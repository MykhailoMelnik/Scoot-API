package com.scoot.api.bucket4j;

import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BucketManagerTest {

    private BucketManager bucketManager;

    @BeforeEach
    void setUp() {
        bucketManager = new BucketManagerImpl();
    }

    @Test
    void testRateLimiting() {
        String apiKey = "testApiKey";
        Bucket bucket = bucketManager.getBucketForApiKey(apiKey);

        for (int i = 0; i < 10; i++) {
            assertTrue(bucket.tryConsume(1));
        }

        for (int i = 0; i < 11; i++) {
            assertFalse(bucket.tryConsume(1));
        }
    }
}