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

    public Map<String, Object> parseFile(final String fileContent, final FileType fileType) {

        Map<String, Object>  modelTypeMap = new HashMap<>();

        if (fileType == FileType.MSPOWERSHELL) {

            try {
                modelTypeMap = MSPowerShellFileAdapter.parseMSPowerShellFile(fileContent);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                LOGGER.info("Exception parsing XML: " + e.getStackTrace());
                LOGGER.info("File is not a valid XML file.");
            }

        } else if (fileType == FileType.NETSTAT) {
            modelTypeMap = netstatFileAdapter.parseNetstatFile(fileContent);
        }

        return modelTypeMap;
    }


}
