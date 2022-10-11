package com.geekglassess.trustnetwork.model;

import com.geekglassess.trustnetwork.entities.Person;
import com.geekglassess.trustnetwork.entities.Topic;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class TopicMapper {
    public HashSet<Topic> mapStringSetToTopicSet(List<String> topicList) {
        Preconditions.checkArgument(topicList != null, "");
        Preconditions.checkArgument(topicList.size() > 0, "Expected non-empty topicList");

        HashSet<Topic> topicSet = new HashSet();
        for (int i = 0; i < topicList.size(); i++) {
            topicSet.add(new Topic((long) i, topicList.get(i)));
        }

        return topicSet;
    }

    public HashSet<Topic> setTopicsPersonValue(HashSet<Topic> topics, Person person) {
        HashSet<Topic> setTopics = new HashSet<>();

        for (Topic topic : topics) {
            setTopics.add(new Topic(topic, person));
        }

        return setTopics;
    }
}
