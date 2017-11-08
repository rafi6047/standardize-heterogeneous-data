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
    private String portNoInRequest;

    public Map<String, Object> parseNetstatFile(final String netstat) {
        Map<String, Object>  modelTypeMap = new HashMap<>();

        String pattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+):([0-9]{1,5})";

        // Create a Pattern object
        Pattern regularExpression = Pattern.compile(pattern);

        List<String> lines = Arrays.asList(netstat.split("\\r\\n|\\n|\\r"));

        Port port;

        for (String line : lines) {
            // Now create matcher object from String to be scanned to find the pattern.
            Matcher matcher = regularExpression.matcher(line);
            if (matcher.find()) {
                String[] serviceNameInRequestList = line.split("/");
                String serviceNameInRequest = serviceNameInRequestList[serviceNameInRequestList.length-1].trim();
                String serviceProtocolInRequest = serviceNameInRequestList[0].trim().split(" ")[0].toLowerCase();

                // first find by port no where entry = manual. if found, see if serviceName = name in the request.
                // If yes, leave it, else add the service name in learnedServiceAliasList if not existing:
                portNoInRequest = matcher.group(2).trim() + "/" + serviceProtocolInRequest;
                port = portRepository.findByDefaultPort(portNoInRequest);

                if (port != null) {
                    if (port.getServiceName() != serviceNameInRequest) {
//                        LOGGER.info("didn't match service name: " + serviceNameInRequest + " for port: " + portNoInRequest
//                                + "\nAdding service name in learnedServiceAliasList");
                        port.addLearnedServiceAliasList(serviceNameInRequest);
                        portRepository.save(port);
                    }
                } else {
                    // If not found by port no, then find by service name
                    port = portRepository.findByServiceName(serviceNameInRequest);
                }

                // if still not found then search service name in learned alias list
                if (port == null) {
                    port = portRepository.findByLearnedServiceAliasListContaining(serviceNameInRequest);
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

        return modelTypeMap;
    }
}