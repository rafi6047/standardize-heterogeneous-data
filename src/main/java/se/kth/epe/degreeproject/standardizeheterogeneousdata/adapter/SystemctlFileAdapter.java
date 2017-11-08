package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SystemctlFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemctlFileAdapter.class);

    public Map<String, Object> parseInstalledFile(final String systemctl) {
        Map<String, Object>  modelTypeMap = new HashMap<>();
        List<String> lines = Arrays.asList(systemctl.split("\\r\\n|\\n|\\r"));

        lines = lines.subList(1, lines.size());

        for (String line : lines) {
            List<String> serviceNames = new ArrayList<>();
            serviceNames.addAll(Arrays.asList(line.split(" ")));
            serviceNames.removeAll(Arrays.asList("", null));
            if (serviceNames.isEmpty()) {
                break;
            }

            LOGGER.info("rafi print: " + serviceNames.get(0));
            if (!serviceNames.get(0).isEmpty()) {
                modelTypeMap.put(serviceNames.get(0), serviceNames.get(0));
            }
        }

        return modelTypeMap;
    }
}