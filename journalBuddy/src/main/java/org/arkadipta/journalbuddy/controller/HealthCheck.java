package org.arkadipta.journalbuddy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck
{
    @GetMapping("/health")
    public String health(){
        return "I am okey !";
    }
}
