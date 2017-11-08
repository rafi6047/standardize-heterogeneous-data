package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InstalledFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstalledFileAdapter.class);

    public Map<String, Object> parseInstalledFile(final String installed) {
        Map<String, Object>  modelTypeMap = new HashMap<>();
        List<String> lines = Arrays.asList(installed.split("\\r\\n|\\n|\\r"));
        lines = lines.subList(2, lines.size());

        for (String line : lines) {
            String[] packageName = line.split(" ");

            if (!packageName[0].isEmpty()) {
                modelTypeMap.put(packageName[0], packageName[0]);
            }
        }

        return modelTypeMap;
    }
}