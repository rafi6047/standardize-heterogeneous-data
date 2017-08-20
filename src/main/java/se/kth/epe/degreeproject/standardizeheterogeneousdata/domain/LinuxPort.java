package se.kth.epe.degreeproject.standardizeheterogeneousdata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class LinuxPort {

    @GraphId
    @JsonIgnore
    private Long nodeId;

    Integer portNo;
    String serviceName;
    String serviceDescription;
    String entryType;
    boolean dependable;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getPortNo() {
        return portNo;
    }

    public void setPortNo(Integer portNo) {
        this.portNo = portNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public boolean isDependable() {
        return dependable;
    }

    public void setDependable(boolean dependable) {
        this.dependable = dependable;
    }

//
//    @Relationship(type="ACTS_IN", direction = Relationship.INCOMING)
//    Set<Person> actors;
//
//    @Relationship(type = "RATED")
//    List<Rating> ratings;
}
