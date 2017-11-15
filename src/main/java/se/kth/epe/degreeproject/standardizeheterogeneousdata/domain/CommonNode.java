package se.kth.epe.degreeproject.standardizeheterogeneousdata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NodeEntity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonNode {

    @GraphId
    @JsonIgnore
    private Long nodeId;

    String classType;
    String name;
    String description;
    String entry;
    String source;
    String tags;
    String keyword;
    String learnedKeywordList;
    List<String> pathToRootList;
    String defaultPort;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
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

    public List<String> getPathToRootList() {
        return pathToRootList;
    }

    public void setPathToRootList(List<String> pathToRootList) {
        this.pathToRootList = pathToRootList;
    }

    public void addPathToRootList(String pathToRoot) {
        if (this.pathToRootList == null) {
            this.pathToRootList = new ArrayList<>();
        }
        this.pathToRootList.add(pathToRoot);
    }

    public String getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(String defaultPort) {
        this.defaultPort = defaultPort;
    }
}
