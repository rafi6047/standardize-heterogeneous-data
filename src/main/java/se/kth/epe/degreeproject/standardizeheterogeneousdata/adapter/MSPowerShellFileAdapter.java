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
import java.util.HashMap;
import java.util.Map;

@Component
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
        NodeList propsList = doc.getElementsByTagName("Props");

        getInfo(modelTypeMap, msList);
        getInfo(modelTypeMap, propsList);

        return modelTypeMap;

    }

    private static void getInfo(Map<String, Object> modelTypeMap, NodeList msList) {
        int counter = 0;
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
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("ClassName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "ClassName: " + sElement.getTextContent() : value + ", ClassName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("ifDesc")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "ifDesc: " + sElement.getTextContent() : value + ", ifDesc: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Description")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "Description: " + sElement.getTextContent() : value + ", Description: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Product")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "Product: " + sElement.getTextContent() : value + ", Product: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Company")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "Company: " + sElement.getTextContent() : value + ", Company: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("name")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "name: " + sElement.getTextContent() : value + ", name: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("objectClass")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "objectClass: " + sElement.getTextContent() : value + ", objectClass: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("CreationClassName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "CreationClassName: " + sElement.getTextContent() : value + ", CreationClassName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("SystemCreationClassName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "SystemCreationClassName: " + sElement.getTextContent() : value + ", SystemCreationClassName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("DriverDescription")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "DriverDescription: " + sElement.getTextContent() : value + ", DriverDescription: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("DriverProvider")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "DriverProvider: " + sElement.getTextContent() : value + ", DriverProvider: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("ElementName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "ElementName: " + sElement.getTextContent() : value + ", ElementName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("InterfaceDescription")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        value = (value == null ? "InterfaceDescription: " + sElement.getTextContent() : value + ", InterfaceDescription: " + sElement.getTextContent());
                    }
                }
            }
            if (key != null && value != null) {
                if (modelTypeMap.containsKey(key)) {
                    key = key + "_" + (counter++) ;
                }
                modelTypeMap.put(key, value);
            }
        }
    }

}
