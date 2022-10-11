package com.geekglassess.trustnetwork.repository;

import com.geekglassess.trustnetwork.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    @Override
    Optional<Person> findById(String s);
}
