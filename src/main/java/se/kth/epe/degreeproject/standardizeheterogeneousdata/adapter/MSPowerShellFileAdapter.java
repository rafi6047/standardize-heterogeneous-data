package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.CommonNode;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.repository.CommonNodeRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MSPowerShellFileAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MSPowerShellFileAdapter.class);

    @Autowired
    private CommonNodeRepository commonNodeRepository;

    public Map<String, Object> parseFile(final String xml) throws ParserConfigurationException, IOException, SAXException {
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

    private void getInfo(Map<String, Object> modelTypeMap, NodeList msList) {

        Long rootNodeId = commonNodeRepository.findFirstByClassType("OntologyRoot").getNodeId();

        int counter = 0;

        for (int ms = 0; ms < msList.getLength(); ms++) {
            String key = null;
            String parsedValue = null;
            String valueFromDB = null;
            List<CommonNode> commonNodeList = new ArrayList<>();

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
                        commonNodeList.addAll(commonNodeRepository.findByKeyword(sElement.getTextContent()));
                        parsedValue = (parsedValue == null ? "DisplayName: " + sElement.getTextContent() : parsedValue + ", DisplayName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Publisher")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "Publisher: " + sElement.getTextContent() : parsedValue + ", Publisher: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("ClassName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "ClassName: " + sElement.getTextContent() : parsedValue + ", ClassName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("ifDesc")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "ifDesc: " + sElement.getTextContent() : parsedValue + ", ifDesc: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Description")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "Description: " + sElement.getTextContent() : parsedValue + ", Description: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Product")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        commonNodeList.addAll(commonNodeRepository.findByKeyword(sElement.getTextContent()));
                        parsedValue = (parsedValue == null ? "Product: " + sElement.getTextContent() : parsedValue + ", Product: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("Company")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "Company: " + sElement.getTextContent() : parsedValue + ", Company: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("name")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        commonNodeList.addAll(commonNodeRepository.findByKeyword(sElement.getTextContent()));
                        parsedValue = (parsedValue == null ? "name: " + sElement.getTextContent() : parsedValue + ", name: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("objectClass")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "objectClass: " + sElement.getTextContent() : parsedValue + ", objectClass: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("CreationClassName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "CreationClassName: " + sElement.getTextContent() : parsedValue + ", CreationClassName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("SystemCreationClassName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "SystemCreationClassName: " + sElement.getTextContent() : parsedValue + ", SystemCreationClassName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("DriverDescription")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "DriverDescription: " + sElement.getTextContent() : parsedValue + ", DriverDescription: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("DriverProvider")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "DriverProvider: " + sElement.getTextContent() : parsedValue + ", DriverProvider: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("ElementName")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        commonNodeList.addAll(commonNodeRepository.findByKeyword(sElement.getTextContent()));
                        parsedValue = (parsedValue == null ? "ElementName: " + sElement.getTextContent() : parsedValue + ", ElementName: " + sElement.getTextContent());
                    } else if (sElement.getAttribute("N").equalsIgnoreCase("InterfaceDescription")) {
                        if (key == null) {
                            key = sElement.getTextContent();
                        }
                        parsedValue = (parsedValue == null ? "InterfaceDescription: " + sElement.getTextContent() : parsedValue + ", InterfaceDescription: " + sElement.getTextContent());
                    }
                }
            }

            commonNodeList.forEach(node -> node.addPathToRootList(commonNodeRepository.findAllPaths(node.getNodeId(), rootNodeId).toString()));

            if (key != null) {
                if (modelTypeMap.containsKey(key)) {
                    key = key + "_" + (counter++) ;
                }

                Map<String, Object> internal = new HashMap<>();
                internal.put("From parsing", parsedValue);
                internal.put("From DB", commonNodeList);

                modelTypeMap.put(key, internal);
            }
        }
    }

}
