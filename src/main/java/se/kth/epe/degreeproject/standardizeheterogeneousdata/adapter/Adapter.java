package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafi on 2017-04-17.
 */
@Component
public class Adapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Adapter.class);

    public List<String> parseFile(final String fileContent) {
        List<String> modelTypeList = new ArrayList<>();
        boolean parseSuccessful = false;

        try {
            modelTypeList = loadXMLFromString(fileContent);
            parseSuccessful = true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.info("Exception parsing XML: " + e.getClass());
            LOGGER.info("File is not XML");
        }

        if (!parseSuccessful) {
            // try other parsing
        }

        return modelTypeList;
    }


    private static List<String> loadXMLFromString(String xml) throws ParserConfigurationException, IOException, SAXException {
        List<String> modelTypeList = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);
        doc.getDocumentElement().normalize();
        LOGGER.info("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("S");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                 if (eElement.getAttribute("N").equalsIgnoreCase("DisplayName")) {
                     LOGGER.info(eElement.getTextContent());
                     modelTypeList.add(eElement.getTextContent());
                 }
            }
        }

        return modelTypeList;

    }
}
