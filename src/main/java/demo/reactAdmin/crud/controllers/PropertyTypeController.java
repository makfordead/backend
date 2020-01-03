package demo.reactAdmin.crud.controllers;

import demo.reactAdmin.crud.entities.PropertyType;
import demo.reactAdmin.crud.repos.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/property")
public class PropertyTypeController {
    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    @GetMapping("getAllPropertyTypes")
    public List<PropertyType> getAllPropertyType()
    {
        return propertyTypeRepository.findAll();
    }
    @PostMapping("createPropertyType")
    public PropertyType createProperty(@RequestBody PropertyType propertyType)
    {

        return propertyTypeRepository.save(propertyType);
    }

}
