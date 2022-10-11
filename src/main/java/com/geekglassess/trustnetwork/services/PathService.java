package com.geekglassess.trustnetwork.services;

import com.geekglassess.trustnetwork.dto.message.MessageDto;
import com.geekglassess.trustnetwork.entities.Person;
import com.geekglassess.trustnetwork.entities.Topic;
import com.geekglassess.trustnetwork.model.TopicMapper;
import com.geekglassess.trustnetwork.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class PathService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TopicMapper topicMapper;

    @Transactional
    public Map<String, Object> findPathFromSenderToTarget(MessageDto messageDto) {
        final Set<Topic> topics = topicMapper.mapStringSetToTopicSet(messageDto.getTopics());
        final Integer minTrustLevel = messageDto.getMinTrustLevel();
        final String fromPersonId = messageDto.getFromPersonId();

        return findAllPersonsInTree(topics, minTrustLevel, fromPersonId);
    }

    private Map<String, Object> findAllPersonsInTree(Set<Topic> topics, Integer minTrustLevel, String fromPersonId) {
        final Set<String> initialAvailablePersons = Set.of(fromPersonId);
        final Set<String> allAvailableUsers = trackAllAvailableUsers(initialAvailablePersons, topics, minTrustLevel);

        return Map.of("from", fromPersonId, "path", allAvailableUsers);
    }

    private Set<String> trackAllAvailableUsers(Set<String> personIdSet, Set<Topic> topics, Integer minTrustLevel) {
        final Set<String> availableUsers = new HashSet<>();
        boolean isAvailablePersons = false;

        for (String personId : personIdSet) {
            final Map<String, Integer> nameIdToTrustLevelRelationMap = personRepository.findById(personId).get().getNameIdToTrustLevelRelationMap();

            for (Map.Entry<String, Integer> stringIntegerEntry : nameIdToTrustLevelRelationMap.entrySet()) {
                Person personKey = personRepository.findById(stringIntegerEntry.getKey()).get();

                if (personParametersIsValid(topics, minTrustLevel, stringIntegerEntry, personKey)) {
                    availableUsers.add(personKey.getId());
                    isAvailablePersons = true;
                }
            }
        }

        if (isAvailablePersons) {
            trackAllAvailableUsers(availableUsers, topics, minTrustLevel);
        }

        return availableUsers;
    }

    private boolean personParametersIsValid(Set<Topic> topics, Integer minTrustLevel, Map.Entry<String, Integer> stringIntegerEntry, Person personKey) {
        return stringIntegerEntry.getValue() >= minTrustLevel && personKey.getTopics().retainAll(topics);
    }
}
