package org.spburegistry.backend.controller;

import org.spburegistry.backend.entity.TestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/test")
    public TestEntity getString() {
        TestEntity user1 = TestEntity.builder().id(1).text("Hi!").build();
        return user1;
    }
}
