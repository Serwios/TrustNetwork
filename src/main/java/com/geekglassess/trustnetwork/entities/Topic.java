package com.geekglassess.trustnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Topic")
@Table(name = "topic")
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="topic")
    private String topic;

    @ManyToOne(optional = false)
    @JoinColumn(name="person_id")
    private Person person;

    public Topic() {}

    public Topic(Long id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public Topic(Topic topic, Person person) {
        this.id = topic.id;
        this.topic = topic.getTopic();
        this.person = person;
    }
}
