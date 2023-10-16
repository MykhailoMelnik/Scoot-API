package com.scoot.api.bucket4j;

import io.github.bucket4j.Bucket;

public interface BucketManager {
    public Bucket getBucketForApiKey(String apiKey);
}