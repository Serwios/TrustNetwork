package com.geekglassess.trustnetwork;

import com.geekglassess.trustnetwork.services.MessageService;
import com.geekglassess.trustnetwork.services.PathService;
import com.geekglassess.trustnetwork.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StartStopTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private PathService pathService;
    @Autowired
    private PersonService personService;

    @Test
    public void startStopTest() {
        log.info("Done!");
    }
}
