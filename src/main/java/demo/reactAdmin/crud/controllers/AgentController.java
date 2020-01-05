package demo.reactAdmin.crud.controllers;

import demo.reactAdmin.ReactAdminDemoApplication;
import demo.reactAdmin.crud.entities.Agent;
import demo.reactAdmin.crud.enums.Status;
import demo.reactAdmin.crud.repos.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @PostMapping("/UpdateAgent/{id}")
    public Agent update(@RequestBody Agent agent, @PathVariable int id)
    {
        agent.setId(id);
        return agentRepository.save(agent);

    }
    @GetMapping("getAgent/{id}")
    public Agent getAgent(@PathVariable int id)
    {
       return agentRepository.findById(id).orElseThrow();
    }

    @PostMapping("deleteAgent/{id}")
    public void deleteAgent(@PathVariable int id)
    {
     agentRepository.deleteById(id);
    }

    @GetMapping()
    public List<Agent> getAgent(@RequestParam String _end,@RequestParam String _start)
    {

return  agentRepository.findAll();
    }

}
