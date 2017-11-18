package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

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

    @Autowired
    private CommonNodeRepository commonNodeRepository;

    public Map<String, Object> parseInstalledFile(final String systemctl) {
        Long rootNodeId = commonNodeRepository.findFirstByClassType("OntologyRoot").getNodeId();

        Map<String, Object>  modelTypeMap = new HashMap<>();
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
                List<CommonNode> commonNodeListFromDB = commonNodeRepository.findByKeyword(keyWord);
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
                commonNodeList.forEach(node -> node.addPathToRootList(commonNodeRepository.findAllPaths(node.getNodeId(), rootNodeId).toString()));

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
}