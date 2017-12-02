package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import info.debatty.java.stringsimilarity.Jaccard;
import info.debatty.java.stringsimilarity.JaroWinkler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.CommonNode;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.repository.CommonNodeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SystemctlFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemctlFileAdapter.class);

    private static final double acceptedSimilarity = 0.5;
    private JaroWinkler jaroWinkler = new JaroWinkler();
    private Jaccard jaccard = new Jaccard();

    @Autowired
    private CommonNodeRepository commonNodeRepository;

    @Autowired
    private CommonNodeUtil commonNodeUtil;

    public Map<String, Object> parseInstalledFile(final String systemctl) {
        List<String> keywordListFromDB = commonNodeUtil.getKeywordListFromDB();

        Map<String, Object> modelTypeMap = new HashMap<>();
        List<String> lines = Arrays.asList(systemctl.split("\\r\\n|\\n|\\r"));

        lines = lines.subList(1, lines.size());
        int counter = 0;

        for (String line : lines) {
            List<CommonNode> commonNodeList = new ArrayList<>();

            List<String> serviceNames = new ArrayList<>();
            serviceNames.addAll(Arrays.asList(line.split(" ")));
            serviceNames.removeAll(Arrays.asList("", null));
            if (serviceNames.isEmpty()) {
                break;
            }

            String key = serviceNames.get(0);
            String keyWord = serviceNames.get(0).replace(".service", "");
            if (!key.isEmpty()) {
                List<CommonNode> commonNodeListFromDB = getCommonNodeList(keywordListFromDB, keyWord);
                if (commonNodeListFromDB.isEmpty()) {
                    commonNodeListFromDB = commonNodeRepository.findByLearnedKeywordListContaining(keyWord);
                    commonNodeList.forEach(node -> {
                        node.setEntry("Learned");
                        node.setSource("Machine learning");
                        node.addLearnedKeywordList(keyWord);
                        commonNodeRepository.save(node);
                    });
                }
                commonNodeList.addAll(commonNodeListFromDB);
                commonNodeList.forEach(node -> node.setPathToRootList(commonNodeUtil.getAllPathsToRoot(node)));

                if (modelTypeMap.containsKey(key)) {
                    key = key + "_" + (counter++);
                }

                Map<String, Object> internal = new HashMap<>();
                internal.put("From parsing", key);
                internal.put("From DB", commonNodeList);

                modelTypeMap.put(key, internal);
            }
        }

        return modelTypeMap;
    }

    private List<CommonNode> getCommonNodeList(List<String> keywordListFromDB, String keywordToMatch) {
        List<CommonNode> commonNodeList = new ArrayList<>();

        double similarityTemp = 0;
        double similarity = 0;
        String keywordWithBestMatch = null;

        for (String keyword : keywordListFromDB) {
            similarityTemp = jaccard.similarity(keyword, keywordToMatch);
            if (similarityTemp >= acceptedSimilarity && similarityTemp > similarity) {
                similarity = similarityTemp;
                keywordWithBestMatch = keyword;
            }
        }
        if (keywordWithBestMatch != null) {
            LOGGER.info("Best match for '" + keywordToMatch + "': '"
                    + keywordWithBestMatch + "' with Jaccard similarity: " + similarity);
            List<CommonNode> commonNodeListLocal = commonNodeRepository.findAllByKeyword(keywordWithBestMatch);
            for (CommonNode commonNode : commonNodeListLocal) {
                commonNode.setJaccardSimilarity(similarity);
            }
            commonNodeList.addAll(commonNodeListLocal);
        }

        return commonNodeList;
    }

}