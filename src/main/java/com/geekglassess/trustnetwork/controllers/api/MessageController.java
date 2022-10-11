package com.geekglassess.trustnetwork.controllers.api;

import com.geekglassess.trustnetwork.dto.message.MessageDto;
import com.geekglassess.trustnetwork.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Map<String, Set<String>> sendMessage(@RequestBody MessageDto messageDto) {
        return messageService.sendMessages(messageDto);
    }
}
