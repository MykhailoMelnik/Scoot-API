package com.scoot.api.exceptionHandler;

import com.scoot.api.exceptionHandler.GlobalExceptionHandler;
import com.scoot.api.exceptionHandler.exception.InvalidRequestException;
import com.scoot.api.exceptionHandler.exception.RateLimitExceededException;
import com.scoot.api.exceptionHandler.exception.UnknownApiKeyException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Before
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleInvalidRequestException() {
        InvalidRequestException ex = new InvalidRequestException("Invalid request");
        ResponseEntity<String> response = globalExceptionHandler.handleInvalidRequestException(ex);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), "Invalid request");
    }

    @Test
    public void testHandleUnknownApiKeyException() {
        UnknownApiKeyException ex = new UnknownApiKeyException("Unknown API key");
        ResponseEntity<String> response = globalExceptionHandler.handleUnknownApiKeyException(ex);

        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
        assertEquals(response.getBody(), "Unknown API key");
    }

    @Test
    public void testHandleRateLimitExceededException() {
        RateLimitExceededException ex = new RateLimitExceededException("Rate limit exceeded");
        ResponseEntity<String> response = globalExceptionHandler.handleRateLimitExceededException(ex);

        assertEquals(response.getStatusCode(), HttpStatus.TOO_MANY_REQUESTS);
        assertEquals(response.getBody(), "Rate limit exceeded");
    }
}
