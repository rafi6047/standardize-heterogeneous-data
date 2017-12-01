package se.kth.epe.degreeproject.standardizeheterogeneousdata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NodeEntity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommonNode {

    @GraphId
    @JsonIgnore
    private Long nodeId;

    String classType;
    String name;
    String producer;
    String description;
    String entry;
    String source;
    String tags;
    String keyword;
    String learnedKeywordList;
    List<String> pathToRootList;
    String defaultPort;
    String version;
    String ESP_DEP;
    String ASLR;
    String SEHOP;
    String UAC;
    String DNSSEC;
    String Encryption;
    String Cryptography;
    String Firewall_Defender;
    String AUTHENTICATION;
    double jaccardSimilarity;
    double rafiSimilarity;
    double jaroWinklerSimilarity;

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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getESP_DEP() {
        return ESP_DEP;
    }

    public void setESP_DEP(String ESP_DEP) {
        this.ESP_DEP = ESP_DEP;
    }

    public String getASLR() {
        return ASLR;
    }

    public void setASLR(String ASLR) {
        this.ASLR = ASLR;
    }

    public String getSEHOP() {
        return SEHOP;
    }

    public void setSEHOP(String SEHOP) {
        this.SEHOP = SEHOP;
    }

    public String getUAC() {
        return UAC;
    }

    public void setUAC(String UAC) {
        this.UAC = UAC;
    }

    public String getDNSSEC() {
        return DNSSEC;
    }

    public void setDNSSEC(String DNSSEC) {
        this.DNSSEC = DNSSEC;
    }

    public String getEncryption() {
        return Encryption;
    }

    public void setEncryption(String encryption) {
        Encryption = encryption;
    }

    public String getCryptography() {
        return Cryptography;
    }

    public void setCryptography(String cryptography) {
        Cryptography = cryptography;
    }

    public String getFirewall_Defender() {
        return Firewall_Defender;
    }

    public void setFirewall_Defender(String firewall_Defender) {
        Firewall_Defender = firewall_Defender;
    }

    public String getAUTHENTICATION() {
        return AUTHENTICATION;
    }

    public void setAUTHENTICATION(String AUTHENTICATION) {
        this.AUTHENTICATION = AUTHENTICATION;
    }

    public double getJaccardSimilarity() {
        return jaccardSimilarity;
    }

    public void setJaccardSimilarity(double jaccardSimilarity) {
        this.jaccardSimilarity = jaccardSimilarity;
    }

    public double getJaroWinklerSimilarity() {
        return jaroWinklerSimilarity;
    }

    public void setJaroWinklerSimilarity(double jaroWinklerSimilarity) {
        this.jaroWinklerSimilarity = jaroWinklerSimilarity;
    }

    public double getRafiSimilarity() {
        return rafiSimilarity;
    }

    public void setRafiSimilarity(double rafiSimilarity) {
        this.rafiSimilarity = rafiSimilarity;
    }
}
