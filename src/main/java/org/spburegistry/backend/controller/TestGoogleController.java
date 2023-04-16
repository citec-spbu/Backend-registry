package org.spburegistry.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestGoogleController {
    @GetMapping("/testGoogle")
    public ResponseEntity<Object> testGoogle(HttpServletRequest request) {
        System.out.println(request);
        return new ResponseEntity<>(request, HttpStatusCode.valueOf(200));
    }
}
