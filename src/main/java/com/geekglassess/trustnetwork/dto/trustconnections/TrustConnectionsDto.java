package com.geekglassess.trustnetwork.dto.trustconnections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrustConnectionsDto {
    private HashMap<String, Byte> personNameToTrustLevel;
}
