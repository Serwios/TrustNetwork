package com.geekglassess.trustnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "Person")
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id")
    private String id;

    @OneToMany(mappedBy="person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Topic> topics;

    @ElementCollection
    @CollectionTable(name = "person_id_trust_level",
            joinColumns = { @JoinColumn(name = "person_id") })
    @MapKeyColumn(name = "target_relation_person")
    @Column(name = "trust_level")
    private Map<String, Integer> nameIdToTrustLevelRelationMap;

    private String receivedMessage;

    public void addNewRelationToPerson(Map<String, Integer> newRelation) {
        nameIdToTrustLevelRelationMap.putAll(newRelation);
    }

    public Person(String id) {
        this.id = id;
    }
}