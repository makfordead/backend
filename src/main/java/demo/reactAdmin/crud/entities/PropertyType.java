package demo.reactAdmin.crud.entities;


import javax.persistence.*;


@Entity
public class PropertyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTYTYPE_ID")
    public Integer id;
    @Column
    public String Name;
    @Column
    public String Description;

    public PropertyType()
    {

    }
    public PropertyType(String name, String description) {
        Name = name;
        Description = description;
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
}
