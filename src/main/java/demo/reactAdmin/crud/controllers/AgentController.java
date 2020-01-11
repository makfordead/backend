package demo.reactAdmin.crud.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import demo.reactAdmin.ReactAdminDemoApplication;
import demo.reactAdmin.crud.entities.Agent;
import demo.reactAdmin.crud.entities.PropertyType;
import demo.reactAdmin.crud.enums.Status;
import demo.reactAdmin.crud.repos.AgentRepository;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/agents")
@CrossOrigin
public class AgentController {
    @Autowired
    AgentRepository agentRepository;
    @PostMapping("")
    public Agent create(@RequestBody Agent agent)
    {
        agentRepository.save(agent);
        return agent;

    }

    @PutMapping("/{id}")
    public Agent update(@PathVariable Integer id,@RequestBody Agent agent)
    {
        agent.setId(id);
        return agentRepository.save(agent);

    }
    @GetMapping("/{id}")
    public Agent getAgent(@PathVariable int id)
    {
       return agentRepository.findById(id).get();
    }
//    @GetMapping()
//    public Agent getagent(@RequestParam Integer id)
//    {
//        return agentRepository.findById(id).get();
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Agent> deleteAgent(@PathVariable int id)
    {
     try {
         Agent agent = agentRepository.findById(id).get();
         agentRepository.deleteById(id);
     }
        catch (Exception ex)
        {
            System.out.println("Not Deleted");
        }
     return new ResponseEntity(HttpStatus.OK);
    }
//    @DeleteMapping()
//    public void deleteAgent(@RequestParam String filter)
//    {
//        System.out.println("working___");
//        Gson gson = new Gson();
//        LinkedList<Integer> id = gson.fromJson(filter,LinkedList.class);
//        for (int i = 0; i < id.size(); i++) {
//            System.out.println(id.get(i));
//
//        }
//     }

//    @GetMapping()
//    public List<Agent> getAgent(@RequestParam String _end,@RequestParam String _start)
//    {
//        List<Agent> list = new LinkedList<>();
//        Integer starting_index = Integer.parseInt(_start);
//        Integer ending_index = Integer.parseInt(_end);
//        for (int i = starting_index; i <=ending_index; i++) {
//            try {
//
//
//                Agent temp = agentRepository.findById(i).get();
//                if (temp.getId() != null)
//                    list.add(temp);
//            }
//            catch (Exception e)
//            {
//
//            }
//            }
//return  list;
//    }
  //  https://www.baeldung.com/spring-response-header

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<String > getagent(@RequestParam(required = false) String _end, @RequestParam(required =
    false) String _start, @RequestParam(required = false) List<Integer> id)
    {

        System.out.println("___________");
        System.out.println("CALLING");
        List<Agent> list = new LinkedList<>();

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Access-Control-Allow-Origin",null);
        if(id==null){
        Integer starting_index = Integer.parseInt(_start);
        Integer ending_index = Integer.parseInt(_end);
        Iterable<Agent> iterable = agentRepository.findAll();
            for (Agent agent:iterable
                 ) {
                list.add(agent);
            }
//        for (int i = starting_index; i <=ending_index; i++) {
//            try {
//
//
//                Agent temp = agentRepository.findById(i).get();
//                if (temp.getId() != null)
//                    list.add(temp);
//            }
//            catch (Exception e)
//            {
//
//            }
//            }
        String json = new Gson().toJson(list );
            httpHeaders.set("X-Total-Count",""+list.size());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(json);
    }
        else {
            Agent[] arr = new Agent[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i]= agentRepository.findById(id.get(i)).get();
            }

            String to_re = new Gson().toJson(arr);
            System.out.println(to_re);
            httpHeaders.set("X-Total-Count",""+arr.length);
            return ResponseEntity.ok().headers(httpHeaders).body(
                    to_re
            );
        }
    }

}
