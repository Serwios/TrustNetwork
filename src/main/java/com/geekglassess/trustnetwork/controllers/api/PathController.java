package com.geekglassess.trustnetwork.controllers.api;

import com.geekglassess.trustnetwork.dto.message.MessageDto;
import com.geekglassess.trustnetwork.services.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PathController {
    @Autowired
    private PathService pathService;

    @PostMapping("/path")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Map<String, Object> getPathToTarget(@RequestBody MessageDto messageDto) {
        return pathService.findPathFromSenderToTarget(messageDto);
    }

}
