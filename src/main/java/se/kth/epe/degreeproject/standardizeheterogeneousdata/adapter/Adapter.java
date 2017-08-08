package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafi on 2017-04-17.
 */
@Component
public class Adapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Adapter.class);

    public List<String> parseFile(final String fileContent, final FileType fileType) {

        List<String> modelTypeList = new ArrayList<>();

        if (fileType == FileType.MSPOWERSHELL) {

            try {
                modelTypeList = MSPowerShellFileAdapter.parseMSPowerShellFile(fileContent);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                LOGGER.info("Exception parsing XML: " + e.getStackTrace());
                LOGGER.info("File is not a valid XML file.");
            }

        } else if (fileType == FileType.NETSTAT) {
            modelTypeList = NetstatFileAdapter.parseNetstatFile(fileContent);
        }

        return modelTypeList;
    }


}
