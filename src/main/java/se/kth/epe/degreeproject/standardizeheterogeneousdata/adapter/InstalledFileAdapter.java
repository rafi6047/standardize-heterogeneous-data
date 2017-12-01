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
public class InstalledFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstalledFileAdapter.class);

    @Autowired
    private CommonNodeRepository commonNodeRepository;

    public Map<String, Object> parseFile(final String installed) {
        Long rootNodeId = commonNodeRepository.findFirstByClassType("OntologyRoot").getNodeId();

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