package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.LinuxPort;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.repository.LinuxPortRepository;

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
    private LinuxPortRepository linuxPortRepository;

    public Map<String, Object> parseNetstatFile(final String netstat) {
        Map<String, Object>  modelTypeMap = new HashMap<>();

        String pattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+):([0-9]{1,5})";

        // Create a Pattern object
        Pattern regularExpression = Pattern.compile(pattern);

        List<String> lines = Arrays.asList(netstat.split("\\r\\n|\\n|\\r"));

        LinuxPort linuxPort;

        for (String line : lines) {
            // Now create matcher object from String to be scanned to find the pattern.
            Matcher matcher = regularExpression.matcher(line);
            if (matcher.find()) {
                linuxPort = linuxPortRepository.findByPortNo(Integer.parseInt(matcher.group(2)));
                modelTypeMap.put(matcher.group(2), linuxPort);
            }
        }

        return modelTypeMap;
    }
}