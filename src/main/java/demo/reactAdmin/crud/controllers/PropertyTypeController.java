package demo.reactAdmin.crud.controllers;

import com.google.gson.Gson;
import demo.reactAdmin.crud.entities.Agent;
import demo.reactAdmin.crud.entities.PropertyType;
import demo.reactAdmin.crud.repos.PropertyTypeRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/propertytypes")
public class PropertyTypeController {
    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    @PutMapping("/{id}")
    public PropertyType update(@PathVariable Integer id,@RequestBody PropertyType property
    )
    {

            property.setId(id);
            return propertyTypeRepository.save(property);

    }
//    @GetMapping()
//    public PropertyType getagent(@RequestParam int id)
//    {
//        return propertyTypeRepository.findById(id).get();
//    }


    @GetMapping("/{id}")
    public PropertyType getAgenti(@PathVariable Integer id)
    {
        System.out.println("CHECKING");
        return propertyTypeRepository.findById(id).get();

    }
//    @GetMapping(params = {"id"})
//    public ResponseEntity<String> getproperty(@RequestParam Integer id)
//    {
//        return ResponseEntity.ok().body(new Gson().toJson(propertyTypeRepository.findById(id).get()));
//    }
    @GetMapping()
    public ResponseEntity<String> getproperty(
            @RequestParam(required = false) String _end,@RequestParam(required =
            false) String _start, @RequestParam(required = false) List<Integer> id
            ) {
        System.out.println("PROPERTY TYPE");
        List<PropertyType> list = new LinkedList<>();

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Access-Control-Allow-Origin",null);
        if(id==null){
            Integer starting_index = Integer.parseInt(_start);
            Integer ending_index = Integer.parseInt(_end);
            for (int i = starting_index; i <=ending_index; i++) {
                try {


                    PropertyType temp = propertyTypeRepository.findById(i).get();
                    if (temp.getId() != null)
                        list.add(temp);
                }
                catch (Exception e)
                {

                }
            }
            String json = new Gson().toJson(list );
            String temp = "items" + "1-3/" + new Gson().toJson(list).getBytes();

            //json= json.replace("Id","id");

            httpHeaders.set("X-Total-Count",""+list.size());
            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(json);
        }
        else {
            httpHeaders.set("X-Total-Count",""+list.size());
            PropertyType[] arr = new PropertyType[id.size()];
            for (int i = 0; i < id.size(); i++) {
                arr[i]=propertyTypeRepository.findById(id.get(i)).get();
            }
            String to_re = new Gson().toJson(arr);
            return ResponseEntity.ok().headers(httpHeaders).body(
                    to_re);
        }
    }
    @PostMapping()
    public PropertyType createProperty(@RequestBody PropertyType propertyType)
    {

        return propertyTypeRepository.save(propertyType);
    }
    @DeleteMapping("/{propertytypeid}")
    public PropertyType deleteproperty(@PathVariable Integer propertytypeid)
    {
        PropertyType propertyType = propertyTypeRepository.findById(propertytypeid).get();
        propertyTypeRepository.deleteById(propertytypeid);
        return propertyType;
    }
    @PutMapping("/")
    public PropertyType updateproperty(@RequestBody PropertyType propertyType,@RequestParam
                                     Integer id)
    {
        propertyType.setId(id);
        return propertyTypeRepository.save(propertyType);
    }
}
