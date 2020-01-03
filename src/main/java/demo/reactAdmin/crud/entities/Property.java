package demo.reactAdmin.crud.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PROPERTY_ID")
    Integer Id;

    @Column
    String Value;

    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    PropertyType propertyType;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


//    public Agent getAgent() {
//        return agent;
//    }

//    public void setAgent(Agent agent) {
//        this.agent = agent;
//    }
}
