package com.geekglassess.trustnetwork.services;

import com.geekglassess.trustnetwork.entities.Person;
import com.geekglassess.trustnetwork.repository.PersonRepository;
import com.geekglassess.trustnetwork.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public void createPerson(Person person) {
        personRepository.save(person);
        topicRepository.saveAll(person.getTopics());
    }

    @Transactional
    public void setPersonTrustRelationMap(String personId, Map<String, Integer> personIdToTrustLevelMap) {
        final Optional<Person> opPerson = personRepository.findById(personId);
        if (opPerson.isPresent()) {
            Person person = opPerson.get();
            person.addNewRelationToPerson(personIdToTrustLevelMap);

            saveNotPresentTargetedPersons(personIdToTrustLevelMap.keySet());
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException("There is no person by id: " + personId);
        }
    }

    private void saveNotPresentTargetedPersons(Set<String> personsIds) {
        for (String personsId : personsIds) {
            if (personRepository.findById(personsId).isEmpty()) {
                personRepository.save(new Person(personsId));
            }
        }
    }
}
