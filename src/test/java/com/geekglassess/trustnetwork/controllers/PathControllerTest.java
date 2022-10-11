package com.geekglassess.trustnetwork.controllers;

import com.geekglassess.trustnetwork.controllers.api.PathController;
import com.geekglassess.trustnetwork.controllers.api.PersonController;
import com.geekglassess.trustnetwork.dto.message.MessageDto;
import com.geekglassess.trustnetwork.dto.person.PersonDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class PathControllerTest {
    @Autowired
    private PathController pathController;
    @Autowired
    private PersonController personController;

    @Before
    public void createInitialDataForDataBase() {
        personController.createPerson(new PersonDto("John", Arrays.asList("magic", "history"), Map.of("Oleh", 5, "Dima", 8)));
        personController.createPerson(new PersonDto("Oleh", Arrays.asList("math", "history"), Map.of("John", 2, "Dima", 2)));
        personController.createPerson(new PersonDto("Dima", Arrays.asList("bio", "history"), Map.of("Oleh", 2, "John", 2)));
    }

    @Test
    public void testSuccessfulPathTracking() {
        MessageDto messageDto = new MessageDto("Hello", Arrays.asList("magic"), "John", 5);
        messageDto.setTopics(Arrays.asList("magic"));
        final Map<String, Object> result = pathController.getPathToTarget(messageDto);

        assertNotNull(result);
        assertEquals("John", result.get("from"));
        assertEquals(Set.of("Oleh", "Dima"), result.get("path"));
    }
}
