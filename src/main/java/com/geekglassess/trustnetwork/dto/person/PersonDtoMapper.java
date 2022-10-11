package com.geekglassess.trustnetwork.dto.person;

import com.geekglassess.trustnetwork.entities.Person;
import com.geekglassess.trustnetwork.entities.Topic;
import com.geekglassess.trustnetwork.model.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PersonDtoMapper {
    @Autowired
    private TopicMapper topicMapper;

    public Person mapFrom(PersonDto personDto) {
        HashSet<Topic> topicsWithPersonValue = null;
        Person person = new Person();
        person.setId(personDto.getId());

        if (personDto.getTopics() != null) {
            HashSet<Topic> topics = topicMapper.mapStringSetToTopicSet(personDto.getTopics());
            topicsWithPersonValue = topicMapper.setTopicsPersonValue(topics, person);
            person.setTopics(topicsWithPersonValue);
        }

        if (personDto.getNameIdToTrustLevelRelationMap() != null) {
            person.setNameIdToTrustLevelRelationMap(personDto.getNameIdToTrustLevelRelationMap());
        }

        return new Person(personDto.getId(), topicsWithPersonValue, person.getNameIdToTrustLevelRelationMap(), null);
    }
}
