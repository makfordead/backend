package demo.reactAdmin.crud.repos;

import demo.reactAdmin.crud.entities.Agent;
import org.springframework.data.repository.PagingAndSortingRepository;
import springboot.rest.repositories.BaseRepository;

public interface AgentRepository extends PagingAndSortingRepository<Agent,Integer> {

}
