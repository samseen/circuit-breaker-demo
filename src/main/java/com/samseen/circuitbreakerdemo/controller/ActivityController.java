package com.samseen.circuitbreakerdemo.controller;

import com.samseen.circuitbreakerdemo.service.ActivityService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("activity")
    @CircuitBreaker(name = "randomActivity", fallbackMethod = "fallbackRandomActivity")
    public ResponseEntity<String> getActivity() {
        return activityService.fetchRandomActivity();
    }

    public ResponseEntity<String> fallbackRandomActivity(Throwable throwable) {
        return ResponseEntity.ok("Service is currently down. Just take a walk.");
    }
}
