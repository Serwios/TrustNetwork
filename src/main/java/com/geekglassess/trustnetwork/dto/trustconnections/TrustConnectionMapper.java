package com.geekglassess.trustnetwork.dto.trustconnections;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TrustConnectionMapper {
    public HashMap<String, Integer> convertUnsafeRawTrustConnectionDtoToMap(Object rawTrustConnection) throws Exception {
        try {
            return (HashMap<String, Integer>) rawTrustConnection;
        } catch (Exception e) {
            throw new Exception("Unexpected exception during raw trust connection converting");
        }
    }
}