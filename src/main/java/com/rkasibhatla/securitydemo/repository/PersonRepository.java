package com.rkasibhatla.securitydemo.repository;

import com.rkasibhatla.securitydemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Person findPersonByUsername(String username);
}
