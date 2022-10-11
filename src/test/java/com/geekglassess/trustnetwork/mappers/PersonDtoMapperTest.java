package com.geekglassess.trustnetwork.mappers;

import com.geekglassess.trustnetwork.dto.person.PersonDto;
import com.geekglassess.trustnetwork.dto.person.PersonDtoMapper;
import com.geekglassess.trustnetwork.entities.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonDtoMapperTest {
    @Autowired
    private PersonDtoMapper personDtoMapper;

    @Test
    public void testSuccessPersonDtoMapping() {
        final Person person = personDtoMapper.mapFrom(new PersonDto("Oleh", Arrays.asList("magic", "anime"), Map.of("Zenoviy", 3)));
        assertNotNull(person);
        assertEquals("Oleh", person.getId());
        assertEquals(Map.of("Zenoviy", 3), person.getNameIdToTrustLevelRelationMap());
    }
}
