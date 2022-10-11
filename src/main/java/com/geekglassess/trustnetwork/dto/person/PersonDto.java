package com.geekglassess.trustnetwork.dto.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private String id;
    private List<String> topics;
    private Map<String, Integer> nameIdToTrustLevelRelationMap;

    public PersonDto(String id, List<String> topics) {
        this.id = id;
        this.topics = topics;
    }
}
