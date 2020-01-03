package demo.reactAdmin.crud.controllers;

import demo.reactAdmin.crud.entities.Agent;
import demo.reactAdmin.crud.entities.Property;
import demo.reactAdmin.crud.entities.PropertyType;
import demo.reactAdmin.crud.repos.AgentRepository;
import demo.reactAdmin.crud.repos.PropertyRepository;
import demo.reactAdmin.crud.repos.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("AgentAndProperty")
public class PropertyAndAgent {
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    PropertyTypeRepository propertyTypeRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @PostMapping("create/{id}/propertyType/{propertyTypeId}")
    public Property createPropertyForAgent(@PathVariable Integer id, @PathVariable Integer
        propertyTypeId  ,@RequestBody Property property)
    {
        Agent agent = agentRepository.findById(id).orElseThrow();
        PropertyType propertyType = propertyTypeRepository.findById(propertyTypeId).orElseThrow();

        property.setAgent(agent);
        property.setPropertyType(propertyType);
        propertyRepository.save(property);
        return property;

    }
    @PostMapping("findPropertiesByAgent/{agentId}")
    public List<Property> findPropertiesByAgent(@PathVariable Integer agentId) {
//        Agent agent = agentRepository.findById(agentId).orElseThrow();
//
        List<Property> properties = new ArrayList<>();

        propertyRepository.findAll().forEach( (e) -> {
            if (e.getAgent().getId().equals(agentId)) properties.add(e);
        });

        return properties;
    }
}
