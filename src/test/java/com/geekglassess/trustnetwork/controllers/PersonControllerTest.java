package com.geekglassess.trustnetwork.controllers;

import com.geekglassess.trustnetwork.controllers.api.PersonController;
import com.geekglassess.trustnetwork.dto.person.PersonDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @Test
    public void testSuccessfulMessageDelivery() {
        PersonDto person = personController.createPerson(new PersonDto("Stepan", Arrays.asList("history", "programming")));
        assertNotNull(person);
        assertEquals("Stepan", person.getId());
        assertEquals(Arrays.asList("history", "programming"), person.getTopics());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTrustLevelBelowThreshold() {
        personController.createPerson(new PersonDto("Stepan", Arrays.asList("history", "programming"), Map.of("John", 0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTrustLevelUnderThreshold() {
        personController.createPerson(new PersonDto("Stepan", Arrays.asList("history", "programming"), Map.of("John", 15)));
    }
}
