package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.CommonNode;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.repository.CommonNodeRepository;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.similarityalgorithms.StringSetSimilarityUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class InstalledFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstalledFileAdapter.class);

    private static final double acceptedSimilarity = 0.5;
    private static final boolean useJaccard = false;

    @Autowired
    private CommonNodeRepository commonNodeRepository;

    @Autowired
    private CommonNodeUtil commonNodeUtil;

    public Map<String, Object> parseFile(final String installed) {
        Map<String, Set<String>> keywordMapSplitted = commonNodeUtil.getKeywordMapSplitted();

        Map<String, Object>  modelTypeMap = new HashMap<>();
        List<String> lines = Arrays.asList(installed.split("\\r\\n|\\n|\\r"));
        lines = lines.subList(2, lines.size());
        int counter = 0;

        for (String line : lines) {
            List<CommonNode> commonNodeList = new ArrayList<>();

            String[] packageName = line.split(" ");

            String key = packageName[0];

            if (!key.isEmpty()) {
                commonNodeList.addAll(commonNodeRepository.findAllByKeyword(key));
                fillUpCommonNodeList(keywordMapSplitted, commonNodeList, key);
                commonNodeList.forEach(node -> node.setPathToRootList(commonNodeUtil.getAllPathsToRoot(node)));

                if (modelTypeMap.containsKey(key)) {
                    key = key + "_" + (counter++) ;
                }

                Map<String, Object> internal = new HashMap<>();
                internal.put("From parsing", key);
                internal.put("From DB", commonNodeList);

                modelTypeMap.put(key, internal);
            }
        }

        return modelTypeMap;
    }

    private void fillUpCommonNodeList(Map<String, Set<String>> keywordMapSplitted, List<CommonNode> commonNodeList, String keywordToMatch) {
        Set<String> keywordSetLocal;
        keywordSetLocal = new HashSet<>(Arrays.asList(keywordToMatch.toLowerCase().split(CommonNodeUtil.regexToSplit)));
        double similarityTemp = 0;
        double similarity = 0;
        String keywordWithBestMatch = null;

        for (Map.Entry<String, Set<String>> entry : keywordMapSplitted.entrySet()) {
            similarityTemp = useJaccard ? StringSetSimilarityUtil.getJaccardSimilarity(keywordSetLocal, entry.getValue())
                    : StringSetSimilarityUtil.getRafiSimilarity(keywordSetLocal, entry.getValue());
            if (similarityTemp >= acceptedSimilarity && similarityTemp > similarity) {
                similarity = similarityTemp;
                keywordWithBestMatch = entry.getKey();
            }
        }
        if (keywordWithBestMatch != null) {
            LOGGER.info("Best match for '" + keywordToMatch + "': '"
                    + keywordWithBestMatch + "' with " + (useJaccard ? "Jaccard " : "Rafi ") + "similarity: " + similarity);
            List<CommonNode> commonNodeListLocal = commonNodeRepository.findAllByKeyword(keywordWithBestMatch);
            for (CommonNode commonNode : commonNodeListLocal) {
                if (useJaccard) {
                    commonNode.setJaccardSimilarity(similarity);
                } else {
                    commonNode.setRafiSimilarity(similarity);
                }
            }
            commonNodeList.addAll(commonNodeListLocal);
        }
    }
}