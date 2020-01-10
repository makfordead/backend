package demo.reactAdmin.crud.entities;

public class wrapper {
    private Integer id;
    private Integer agentId;
    private Integer propertytypeId;
    private String value;
    public Integer getId() {
        return id;
    }

    public wrapper(Integer id, Integer agentId, Integer propertytypeid, String value) {
        this.id = id;
        this.agentId = agentId;
        this.propertytypeId = propertytypeid;
        this.value = value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getPropertytypeid() {
        return propertytypeId;
    }

    public void setPropertytypeid(Integer propertytypeid) {
        this.propertytypeId = propertytypeid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
