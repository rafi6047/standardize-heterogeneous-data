package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rafi on 2017-04-17.
 */
@Component
public class Adapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Adapter.class);

    @Autowired
    private NetstatFileAdapter netstatFileAdapter;

    @Autowired
    private MSPowerShellFileAdapter msPowerShellFileAdapter;

    @Autowired
    private InstalledFileAdapter installedFileAdapter;

    @Autowired
    private SystemctlFileAdapter systemctlFileAdapter;

    @Autowired
    private NmapFileAdapter nmapFileAdapter;

    public Map<String, Object> parseFile(final String fileContent, final FileType fileType) {

        Map<String, Object>  modelTypeMap = new HashMap<>();

        switch (fileType) {
            case NETSTAT:
                modelTypeMap = netstatFileAdapter.parseNetstatFile(fileContent);
                break;
            case MSPOWERSHELL:
                try {
                    modelTypeMap = msPowerShellFileAdapter.parseMSPowerShellFile(fileContent);
                } catch (ParserConfigurationException | SAXException | IOException e) {
                    LOGGER.info("Exception parsing XML: " + e.getStackTrace());
                    LOGGER.info("File is not a valid XML file.");
                }
                break;
            case INSTALLED:
                modelTypeMap = installedFileAdapter.parseInstalledFile(fileContent);
                break;
            case systemctl:
                modelTypeMap = systemctlFileAdapter.parseInstalledFile(fileContent);
                break;
            case nmap:
                modelTypeMap = nmapFileAdapter.parseNmapFile(fileContent);
                break;
        }

        return modelTypeMap;
    }


}
