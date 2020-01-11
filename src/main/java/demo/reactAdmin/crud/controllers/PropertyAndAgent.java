package demo.reactAdmin.crud.controllers;
import com.google.gson.Gson;
import demo.reactAdmin.crud.entities.Agent;
import demo.reactAdmin.crud.entities.Property;
import demo.reactAdmin.crud.entities.PropertyType;
import demo.reactAdmin.crud.entities.wrapper;
import demo.reactAdmin.crud.repos.AgentRepository;
import demo.reactAdmin.crud.repos.PropertyRepository;
import demo.reactAdmin.crud.repos.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/property")
public class PropertyAndAgent {
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    PropertyTypeRepository propertyTypeRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @CrossOrigin
    @PostMapping()
    public Property createPropertyForAgent(@RequestBody Map<String,String> map) {
        Integer id = Integer.parseInt(map.get("agentId"));
        Integer propertyTypeId  = Integer.parseInt(map.get("propertytypeId"));
        Agent agent = agentRepository.findById(id).orElseThrow();
        PropertyType propertyType = propertyTypeRepository.findById(propertyTypeId).orElseThrow();
        Property property = new Property();
        property.setValue(map.get("value"));
        property.setAgent(agent);
        System.out.println(agent);
        property.setPropertyType(propertyType);
        propertyRepository.save(property);
        return property;
    }

    @PostMapping("findPropertiesByAgent/{agentId}")
    public List<Property> findPropertiesByAgent(@PathVariable Integer agentId) {
//        Agent agent = agentRepository.findById(agentId).orElseThrow();
//
        List<Property> properties = new ArrayList<>();

        propertyRepository.findAll().forEach((e) -> {
            if (e.getAgent().getId().equals(agentId)) properties.add(e);
        });

        return properties;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteproperty(@PathVariable Integer id) {
        System.out.println("IN DELETE PROPERTY METHOD");
        final Optional<Property> property = propertyRepository.findById(id);

       try {
           if (property.isPresent()) {
               property.get().setAgent(null);
               property.get().setPropertyType(null);
               propertyRepository.save(property.get());
               propertyRepository.delete(property.get());
           }
       }
       catch (Exception e) {
           System.out.println("Record Deleted");
       }    
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("updateProperty/{agentid}/{propertytypeid}/{propertyid}")
    public void updateProperty(@PathVariable Integer agentid, @PathVariable Integer propertytypeid, @RequestBody Property
            property, @PathVariable Integer propertyid) {
        property.setPropertyType(propertyTypeRepository.findById(propertytypeid).get());
        property.setAgent(agentRepository.findById(agentid).get());
        property.setId(propertyid);
        propertyRepository.save(property);
    }

    @GetMapping()
    public ResponseEntity<String> getAllPropertyType(
            @RequestParam String _end, @RequestParam String _start,
            @RequestParam(required = false) Integer opt) {
       // if (opt == null) {
            List<Property> list = new LinkedList<>();
            list = propertyRepository.findAll();
            List<wrapper> wr = new LinkedList<>();
            Gson gson = new Gson();
            // String json = gson.toJson(user);
            wrapper[] arr = new wrapper[list.size()];
            for (int i = 0; i < arr.length; i++) {
                wr.add(new wrapper(
                        list.get(i).getId(), list.get(i).getAgent().id,
                        list.get(i).getPropertyType().getId(),
                        list.get(i).getValue()
                ));
                //   strArr.add(gson.toJson(temp).substring(1,gson.toJson(temp).length()));
            }


            //    String temp = "items" + "1-3/" + new Gson().toJson(list).getBytes();
            String target = gson.toJson(wr);
            // target=target.replace("\\","");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-Total-Count", "" + list.size());
            httpHeaders.add("Access-Control-Allow-Origin", null);

            return ResponseEntity.ok().headers(httpHeaders).body(target);


    //    }


    }
        @GetMapping("/{id}")
    public Property getpropertybyid(@PathVariable Integer id)
        {
            return propertyRepository.findById(id).get();
        }
        @PutMapping("/{id}")
    public Property updateproperty(@RequestBody Map<String,String> map)
        {
            System.out.println("__________+++++++++++++++++________________________");
                String agentId = map.get("agentId");
            String id =map.get("id");
            String propertytypeId = map.get("propertytypeId");
            String val = map.get("value");
            System.out.println("CALLING THIS METHOD");
            Integer idOfProperty= Integer.parseInt(id);
            Integer idOfAgent = Integer.parseInt(agentId);
            Integer idOfPropertyType = Integer.parseInt(propertytypeId);
           Property property = propertyRepository.getOne(idOfProperty);
           PropertyType propertyType = propertyTypeRepository.getOne(idOfPropertyType);
           property.setPropertyType(propertyType);
           Agent agent = agentRepository.findById(idOfAgent).get();
           property.setAgent(agent);
           property.setValue(val);
           propertyRepository.save(property);

            System.out.println("_________________________");
            return property;
        }
}
