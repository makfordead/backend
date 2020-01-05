package demo.reactAdmin.crud.controllers;

import demo.reactAdmin.ReactAdminDemoApplication;
import demo.reactAdmin.crud.entities.Agent;
import demo.reactAdmin.crud.enums.Status;
import demo.reactAdmin.crud.repos.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/agents")
@CrossOrigin
public class AgentController {
    @Autowired
    AgentRepository agentRepository;
    @CrossOrigin
    @PostMapping()
    public Agent create(@RequestBody Agent agent)
    {
        agentRepository.save(agent);
        return agent;

    }
    @PutMapping()
    public Agent update(@RequestBody Agent agent)
    {

        return agentRepository.save(agent);

    }
    @GetMapping("/{id}")
    public Agent getAgent(@PathVariable int id)
    {
       return agentRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable int id)
    {
     agentRepository.deleteById(id);
    }

    @GetMapping()
    public List<Agent> getAgent(@RequestParam String _end,@RequestParam String _start)
    {
        List<Agent> list = new LinkedList<>();
        Integer starting_index = Integer.parseInt(_start);
        Integer ending_index = Integer.parseInt(_end);
        for (int i = starting_index; i <=ending_index; i++) {
            try {


                Agent temp = agentRepository.findById(i).get();
                if (temp.getId() != null)
                    list.add(temp);
            }
            catch (Exception e)
            {
                
            }
            }
return  list;
    }

}
