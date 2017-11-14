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
    String description;
    String entry;
    String source;
    String keyword;
    String learnedKeywordList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeywordList(String keyword) {
        this.keyword = keyword;
    }

    public String getLearnedKeywordList() {
//        if (learnedServiceAliasList == null) {
//            learnedServiceAliasList = "";
//        }
        return learnedKeywordList;
    }
    public void addLearnedKeywordList(String serviceAlias) {
        if (learnedKeywordList == null) {
            setLearnedKeywordList(serviceAlias);
            return;

        }

        ArrayList<String> learnedServiceAliasList = new ArrayList<>(Arrays.asList(getLearnedKeywordList().split(",")));

        if (!learnedServiceAliasList.contains(serviceAlias)) {
            learnedServiceAliasList.add(serviceAlias);

            setLearnedKeywordList(String.join(",", learnedServiceAliasList));
        }
    }

    public void setLearnedKeywordList(String learnedKeywordList) {
        this.learnedKeywordList = learnedKeywordList;
    }
}
