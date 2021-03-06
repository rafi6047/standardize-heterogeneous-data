package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.neo4j.ogm.response.model.RelationshipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.CommonNode;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.repository.CommonNodeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CommonNodeUtil {

    public static final String regexToSplit = "[ |,|-]";

    @Autowired
    private CommonNodeRepository commonNodeRepository;

    public Map<String, Set<String>> getKeywordMapSplitted() {
        List<String> keywordList = commonNodeRepository.findAllKeywords();

        Map<String, Set<String>> keywordMapSplitted = new HashMap<>();

        for (String keyword : keywordList) {
            keywordMapSplitted.put(keyword, new HashSet<>(Arrays.asList(keyword.toLowerCase().split(regexToSplit))));
        }

        return keywordMapSplitted;
    }

    public List<String> getKeywordListFromDB() {
        List<String> keywordList = commonNodeRepository.findAllKeywords();

        return keywordList;
    }

    public Long getRootNode() {
        Long rootNodeId = commonNodeRepository.findFirstByClassType("OntologyRoot").getNodeId();

        return rootNodeId;
    }

    public List<String> getAllPathsToRoot(CommonNode node) {
        List<String> allPathsStringList = new ArrayList<>();
        Iterable<Map<String, Iterable<Object>>> allPathsToRoot = commonNodeRepository.findAllPaths(node.getNodeId(), getRootNode());
        for (Map<String, Iterable<Object>> stringIterableMap : allPathsToRoot) {
            String thisPath = null;
            for (Map.Entry<String, Iterable<Object>> entry : stringIterableMap.entrySet()) {
                if (entry.getKey().equals("nodes")) {
                    for (Object o : entry.getValue()) {
                        if (thisPath == null) {
                            thisPath = ((CommonNode) o).getClassType();
                        } else {
                            thisPath = thisPath + " -> " + ((CommonNode) o).getClassType();
                        }
                    }
                } else if (entry.getKey().equals("relationships")) {
                    for (Object o : entry.getValue()) {
                        if (thisPath != null) {
                            thisPath = thisPath.replaceFirst(" -> ", " -[" + ((RelationshipModel) o).getType() + "]-> " );
                        }
                    }
                }
            }
            if (thisPath != null) {
                allPathsStringList.add(thisPath);
            }
        }
        return allPathsStringList;
    }

}
