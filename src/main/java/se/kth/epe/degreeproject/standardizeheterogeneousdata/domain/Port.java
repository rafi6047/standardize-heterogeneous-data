package se.kth.epe.degreeproject.standardizeheterogeneousdata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.Arrays;

@NodeEntity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Port {

    @GraphId
    @JsonIgnore
    private Long nodeId;

    String defaultPort;
    String classType;
    String serviceName;
    String serviceDescription;
    String entry;
    String source;
    String learnedServiceAliasList;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(String defaultPort) {
        this.defaultPort = defaultPort;
    }

    public String getClassType() {
        return classType;
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

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLearnedServiceAliasList() {
//        if (learnedServiceAliasList == null) {
//            learnedServiceAliasList = "";
//        }
        return learnedServiceAliasList;
    }
    public void addLearnedServiceAliasList(String serviceAlias) {
        if (learnedServiceAliasList == null) {
            setLearnedServiceAliasList(serviceAlias);
            return;

        }

        ArrayList<String> learnedServiceAliasList = new ArrayList<>(Arrays.asList(getLearnedServiceAliasList().split(",")));

        if (!learnedServiceAliasList.contains(serviceAlias)) {
            learnedServiceAliasList.add(serviceAlias);

            setLearnedServiceAliasList(String.join(",", learnedServiceAliasList));
        }
    }

    public void setLearnedServiceAliasList(String learnedServiceAliasList) {
        this.learnedServiceAliasList = learnedServiceAliasList;
    }
}
