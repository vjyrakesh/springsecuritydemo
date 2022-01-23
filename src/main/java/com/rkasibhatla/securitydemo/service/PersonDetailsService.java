package com.rkasibhatla.securitydemo.service;

import com.rkasibhatla.securitydemo.entity.Person;
import com.rkasibhatla.securitydemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService implements UserDetailsService {


    private PersonRepository personRepository;

    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Person person = personRepository.findPersonByUsername(username);
            if(person != null) {
//                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//                String password = passwordEncoder.encode(person.getPassword());

                return User.withUsername(person.getUsername()).password(person.getPassword()).accountLocked(!person.isEnabled()).roles(person.getRole()).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new UsernameNotFoundException(username);
    }
}
