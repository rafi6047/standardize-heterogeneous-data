package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.Port;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.repository.PortRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NmapFileAdapter extends NetstatFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NmapFileAdapter.class);

    public Map<String, Object> parseNmapFile(final String nmap) {
        Map<String, Object>  modelTypeMap = new HashMap<>();

        String portNoInRequest;

        String pattern = "([0-9]{1,5})\\/(tcp|udp)\\ *([a-zA-Z]+)\\ +([a-zA-Z]+)";

        // Create a Pattern object
        Pattern regularExpression = Pattern.compile(pattern);

        List<String> lines = Arrays.asList(nmap.split("\\r\\n|\\n|\\r"));

        for (String line : lines) {
            // Now create matcher object from String to be scanned to find the pattern.
            Matcher matcher = regularExpression.matcher(line);
            if (matcher.find()) {
                String serviceNameInRequest = matcher.group(4).trim();
                String serviceProtocolInRequest = matcher.group(2).trim().toLowerCase();

                portNoInRequest = matcher.group(1).trim() + "/" + serviceProtocolInRequest;

                getApplicationInfoFromPortAndServiceName(modelTypeMap, portNoInRequest, serviceNameInRequest);
            }
        }
        return modelTypeMap;

    }

}