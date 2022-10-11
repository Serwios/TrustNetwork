package com.geekglassess.trustnetwork.controllers.api;

import com.geekglassess.trustnetwork.dto.person.PersonDto;
import com.geekglassess.trustnetwork.dto.person.PersonDtoMapper;
import com.geekglassess.trustnetwork.dto.trustconnections.TrustConnectionMapper;
import com.geekglassess.trustnetwork.model.TrustLevelValidator;
import com.geekglassess.trustnetwork.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDtoMapper personDtoMapper;
    @Autowired
    private TrustConnectionMapper trustConnectionMapper;
    @Autowired
    private TrustLevelValidator trustLevelValidator;

    @PostMapping("/people")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PersonDto createPerson(@RequestBody PersonDto person) {
        trustLevelValidator.validateTrustMap(person);
        personService.createPerson(personDtoMapper.mapFrom(person));
        return person;
    }

    @PostMapping("/people/{id}/trust_connections")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void setTrustConnections(@PathVariable String id, @RequestBody Object rawTrustConnection) throws Exception {
        final HashMap<String, Integer> personIdToTrustLevelMap = trustConnectionMapper.convertUnsafeRawTrustConnectionDtoToMap(rawTrustConnection);
        personService.setPersonTrustRelationMap(id, personIdToTrustLevelMap);
    }
}
