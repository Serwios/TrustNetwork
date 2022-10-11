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
public class MessageService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TopicMapper topicMapper;

    @Transactional
    public Map<String, Set<String>> sendMessages(MessageDto messageDto) {
        final Set<Topic> topics = topicMapper.mapStringSetToTopicSet(messageDto.getTopics());
        final Integer minTrustLevel = messageDto.getMinTrustLevel();
        final String fromPersonId = messageDto.getFromPersonId();

        return sendMessagesToAvailableUsers(topics, minTrustLevel, fromPersonId, messageDto.getText());
    }

    private Map<String, Set<String>> sendMessagesToAvailableUsers(Set<Topic> topics, Integer minTrustLevel, String fromPersonId, String text) {
        final Set<String> initialAvailablePersons = Set.of(fromPersonId);
        final Set<String> allAvailableUsers = processAllAvailablePersonsInTree(initialAvailablePersons, topics, minTrustLevel, text);

        return Map.of(fromPersonId, allAvailableUsers);
    }

    private Set<String> processAllAvailablePersonsInTree(Set<String> personIdSet, Set<Topic> topics, Integer minTrustLevel, String text) {
        final Set<String> availableUsers = new HashSet<>();
        boolean isAvailablePersons = false;

        for (String personId : personIdSet) {
            final Map<String, Integer> nameIdToTrustLevelRelationMap = personRepository.findById(personId).get().getNameIdToTrustLevelRelationMap();

            for (Map.Entry<String, Integer> stringIntegerEntry : nameIdToTrustLevelRelationMap.entrySet()) {
                Person personKey = personRepository.findById(stringIntegerEntry.getKey()).get();

                if (personParametersIsValid(topics, minTrustLevel, stringIntegerEntry, personKey) && !personContainsText(text, personKey)) {
                    personKey.setReceivedMessage(text);
                    personRepository.save(personKey);
                    availableUsers.add(personKey.getId());
                    isAvailablePersons = true;
                }
            }
        }

        if (isAvailablePersons) {
            processAllAvailablePersonsInTree(availableUsers, topics, minTrustLevel, text);
        }

        return availableUsers;
    }

    private boolean personParametersIsValid(Set<Topic> topics, Integer minTrustLevel, Map.Entry<String, Integer> stringIntegerEntry, Person personKey) {
        return stringIntegerEntry.getValue() >= minTrustLevel && personKey.getTopics().retainAll(topics);
    }

    private boolean personContainsText(String text, Person personKey) {
        return text.equals(personKey.getReceivedMessage());
    }
}
