package com.samseen.circuitbreakerdemo.service;

import com.samseen.circuitbreakerdemo.model.response.ActivityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityService {
    private final RestTemplate restTemplate;
    private final String BORED_API = "https://www.boredapi.com/api/activity";

    public ResponseEntity<String> fetchRandomActivity() {
        ResponseEntity<ActivityResponse> activityResponse = restTemplate
                .getForEntity(BORED_API, ActivityResponse.class);
        ActivityResponse responseBody = activityResponse.getBody();
        return ResponseEntity.ok(responseBody.getActivity());
    }
}
