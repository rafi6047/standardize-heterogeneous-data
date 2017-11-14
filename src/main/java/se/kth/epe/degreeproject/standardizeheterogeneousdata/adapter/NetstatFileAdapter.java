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
public class NetstatFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NetstatFileAdapter.class);

    @Autowired
    private PortRepository portRepository;

    public Map<String, Object> parseNetstatFile(final String netstat) {
        Map<String, Object>  modelTypeMap = new HashMap<>();

        String portNoInRequest;

        String pattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+):([0-9]{1,5})";

        // Create a Pattern object
        Pattern regularExpression = Pattern.compile(pattern);

        List<String> lines = Arrays.asList(netstat.split("\\r\\n|\\n|\\r"));

        for (String line : lines) {
            // Now create matcher object from String to be scanned to find the pattern.
            Matcher matcher = regularExpression.matcher(line);
            if (matcher.find()) {
                String[] serviceNameInRequestList = line.split("/");
                String serviceNameInRequest = serviceNameInRequestList[serviceNameInRequestList.length-1].trim();
                String serviceProtocolInRequest = serviceNameInRequestList[0].trim().split(" ")[0].toLowerCase();

                portNoInRequest = matcher.group(2).trim() + "/" + serviceProtocolInRequest;

                getApplicationInfoFromPortAndServiceName(modelTypeMap, portNoInRequest, serviceNameInRequest);
            }
        }

        return modelTypeMap;
    }

    protected void getApplicationInfoFromPortAndServiceName(Map<String, Object> modelTypeMap,
                                                            String portNoInRequest, String serviceNameInRequest) {
        Port port;
        // first find by port no where entry = manual. if found, see if serviceName = name in the request.
        // If yes, leave it, else add the service name in learnedKeywordList if not existing:
        port = portRepository.findByDefaultPort(portNoInRequest);

        if (port != null) {
            if (port.getServiceName() != serviceNameInRequest) {
//                        LOGGER.info("didn't match service name: " + serviceNameInRequest + " for port: " + portNoInRequest
//                                + "\nAdding service name in learnedServiceAliasList");
                port.addLearnedKeywordList(serviceNameInRequest);
                portRepository.save(port);
            }
        } else {
            // If not found by port no, then find by keyword
            port = portRepository.findByKeyword(serviceNameInRequest);
        }

        // if still not found then search service name in learned keyword list
        if (port == null) {
            port = portRepository.findByLearnedKeywordListContaining(serviceNameInRequest);
            if (port != null) {
                port.setEntry("learned");
                port.setSource("Machine learning");
            }
        }

//                Iterable<Map<String, Object>> relation =
//                        portRepository.findByPortNoTillRoot(portNoInRequest);
//
//                relation.forEach(rel -> rel.entrySet().forEach(entry -> {
//                            LOGGER.info("rafiprint: " + entry.getKey() + ": " + entry.getValue());
//                            LOGGER.info("rafiprint1: " + entry.getValue());
//                        }
//                ));

//                Iterable<Map<String, Object>> result = portRepository.myCustomQuery("hi");


        modelTypeMap.put(portNoInRequest, port);
    }
}