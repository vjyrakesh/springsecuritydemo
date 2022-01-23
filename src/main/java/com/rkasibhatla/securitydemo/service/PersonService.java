package com.rkasibhatla.securitydemo.service;

import com.rkasibhatla.securitydemo.dto.PersonDto;
import com.rkasibhatla.securitydemo.entity.Person;
import com.rkasibhatla.securitydemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public void registerUser(PersonDto personDto) {
        Person person = new Person();
        person.setUsername(personDto.getUsername());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setEnabled(true);
        person.setRole("USER");
        personRepository.save(person);
    }
}
