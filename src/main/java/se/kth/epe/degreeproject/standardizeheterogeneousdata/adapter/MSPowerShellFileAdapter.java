package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MSPowerShellFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MSPowerShellFileAdapter.class);

    public static Map<String, Object> parseMSPowerShellFile(final String xml) throws ParserConfigurationException, IOException, SAXException {
        Map<String, Object> modelTypeMap = new HashMap<>();

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
                    modelTypeMap.put(eElement.getTextContent(), eElement.getTextContent());
                }
            }
        }

        return modelTypeMap;

    }
}
