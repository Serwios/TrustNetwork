package com.geekglassess.trustnetwork.model;

import com.geekglassess.trustnetwork.dto.person.PersonDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TrustLevelValidator {
    private final int MIN_TRUST_LEVEL = 1;
    private final int MAX_TRUST_LEVEL = 10;

    public void validateTrustMap(PersonDto person) {
        if (person.getNameIdToTrustLevelRelationMap() != null) {
            if (!isTrustLevelMapValid(person.getNameIdToTrustLevelRelationMap())) {
                throw new IllegalArgumentException("Trust level id is not in boundaries");
            }
        }
    }

    public boolean isTrustLevelMapValid(Map<String, Integer> trustLevelMap) {
        for (Integer value : trustLevelMap.values()) {
            if (value < MIN_TRUST_LEVEL || value > MAX_TRUST_LEVEL) {
                return false;
            }
        }

        return true;
    }
}
