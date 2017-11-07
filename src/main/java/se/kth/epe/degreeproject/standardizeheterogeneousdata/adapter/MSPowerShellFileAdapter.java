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

        NodeList msList = doc.getElementsByTagName("MS");

        for (int ms = 0; ms < msList.getLength(); ms++) {
            String key = null;
            String value = null;

            Node msNode = msList.item(ms);
            Element msElement = (Element) msNode;

            NodeList sList = msElement.getElementsByTagName("S");
            for ( int i = 0; i < sList.getLength(); i++) {

                Node sNode = sList.item(i);

                if (sNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element sElement = (Element) sNode;

                    if (sElement.getAttribute("N").equalsIgnoreCase("DisplayName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "DisplayName: " + sElement.getTextContent() : value + ", DisplayName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Publisher")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "Publisher: " + sElement.getTextContent() : value + ", Publisher: " + sElement.getTextContent());
                    }
                }
            }
            if (key != null && value != null) {
                modelTypeMap.put(key, value);
            }
        }


        NodeList propsList = doc.getElementsByTagName("Props");

        for (int propsCounter = 0; propsCounter < propsList.getLength(); propsCounter++) {
            String key = null;
            String value = null;

            Node msNode = propsList.item(propsCounter);
            Element msElement = (Element) msNode;

            NodeList sList = msElement.getElementsByTagName("S");
            for ( int i = 0; i < sList.getLength(); i++) {

                Node sNode = sList.item(i);

                if (sNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element sElement = (Element) sNode;

                    if (sElement.getAttribute("N").equalsIgnoreCase("name")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "name: " + sElement.getTextContent() : value + ", name: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("objectClass")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "objectClass: " + sElement.getTextContent() : value + ", objectClass: " + sElement.getTextContent());
                    }
                }
            }
            if (key != null && value != null) {
                modelTypeMap.put(key, value);
            }
        }

        return modelTypeMap;

    }
}
