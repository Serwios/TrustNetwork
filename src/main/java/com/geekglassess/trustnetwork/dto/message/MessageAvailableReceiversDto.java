package com.geekglassess.trustnetwork.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageAvailableReceiversDto {
    private Map<String, Set<String>> senderToReceiverIds;
}
