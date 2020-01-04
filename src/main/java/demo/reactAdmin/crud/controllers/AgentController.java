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
@RequestMapping("api/v1/agents")
@CrossOrigin
public class AgentController {
    @Autowired
    AgentRepository agentRepository;
    @CrossOrigin
    @PostMapping("/CreateAgent")
    public Agent create(@RequestBody Agent agent)
    {
        agentRepository.save(agent);
        System.out.println("____");
        System.out.println("GETTING AGENT");
        List<Agent> list =agentRepository.findAll();
        for (Agent temp : list
        ) {
            System.out.println(temp);
        }
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

    @GetMapping("/getAllAgents")
    public List<Agent> getAgent()
    {
        return agentRepository.findAll();

    }
}
