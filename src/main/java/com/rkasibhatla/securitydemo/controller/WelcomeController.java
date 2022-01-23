package com.rkasibhatla.securitydemo.controller;

import com.rkasibhatla.securitydemo.dto.PersonDto;
import com.rkasibhatla.securitydemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    PersonService personService;

    @GetMapping("/authhello")
    public String authenticatedHello() {
        return "Authenticated hello";
    }

    @GetMapping("/noauthhello")
    public String unAuthenticatedHello() {
        return "Un-authenticated hello";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody PersonDto personDto) {
        personService.registerUser(personDto);
        return ResponseEntity.ok().build();
    }
}
