package com.scoot.api.bucket4j;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BucketManagerImpl implements BucketManager {

    private final Map<String, Bucket> externalUserBuckets;

    public BucketManagerImpl() {
        this.externalUserBuckets = new ConcurrentHashMap<>();
    }

    public Bucket getBucketForApiKey(String apiKey) {
        return externalUserBuckets.computeIfAbsent(apiKey, k -> createBucket());
    }

    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}