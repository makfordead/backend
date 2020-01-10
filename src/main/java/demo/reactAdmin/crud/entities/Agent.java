package demo.reactAdmin.crud.entities;

import demo.reactAdmin.crud.enums.Status;
import demo.reactAdmin.crud.repos.AgentRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public String Name;
    @Column
    public String Description;
    @Column
    public Status Status;
    @Temporal(TemporalType.TIMESTAMP)
    public Date Date;

    public Agent()
    {

    }


    public Agent( String name, String description, demo.reactAdmin.crud.enums.Status status, java.util.Date date) {

        Name = name;
        Description = description;
        Status = status;
        Date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public demo.reactAdmin.crud.enums.Status getStatus() {
        return Status;
    }

    public void setStatus(demo.reactAdmin.crud.enums.Status status) {
        Status = status;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "Id=" + id +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Status=" + Status +
                ", Date=" + Date +
                '}';
    }
}
