package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetstatFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NetstatFileAdapter.class);

    public static List<String> parseNetstatFile(final String netstat) {
        List<String> modelTypeList = new ArrayList<>();

        String pattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+):([0-9]{1,5})";

        // Create a Pattern object
        Pattern regularExpression = Pattern.compile(pattern);

        List<String> lines = Arrays.asList(netstat.split("\\r\\n|\\n|\\r"));
        LinuxPort.LinuxService linuxService;
        System.out.println("rafi: " + lines.size());
        for (String line : lines) {
            // Now create matcher object from String to be scanned to find the pattern.
            Matcher matcher = regularExpression.matcher(line);
            if (matcher.find()) {
                linuxService = LinuxPort.getInstance().getServiceForPort(Integer.parseInt(matcher.group(2)));
                modelTypeList.add(matcher.group(2) + ":" + line.substring(line.lastIndexOf("/") + 1).trim()
                        + " = " + (linuxService == null ? "not found" : linuxService.serviceName
                        + " (" + linuxService.serviceDescription + ")") );
            }
        }

        Collections.sort(modelTypeList);
        return modelTypeList;
    }
}