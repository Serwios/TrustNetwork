package com.geekglassess.trustnetwork.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MessageDto {
    private String text;
    private List<String> topics;
    private String fromPersonId;
    private Integer minTrustLevel;
}
