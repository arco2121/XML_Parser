package Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XMLParser {

    static final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    static DocumentBuilder docBuilder;
    static Document xmldoc = null;
    private static String root;

    static {
        try {
            docBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    static void changeFile(String path, String rootTag) throws IOException, SAXException {
        File file = new File(path);
        root = rootTag;
        xmldoc = docBuilder.parse(file);
    }
    static void changeFile(String path) throws IOException, SAXException {
        File file = new File(path);
        xmldoc = docBuilder.parse(file);
    }

    static NodeList getRoot() {
        return getNode(root);
    }
    
    static Element getRootAsElement() {
        if(xmldoc == null)
            return null;
        return xmldoc.getDocumentElement();
    }

    static NodeList getNode(String nodeName) {
        if(xmldoc == null || nodeName == null)
            return null;
        return xmldoc.getElementsByTagName(nodeName);
    }

    static Node getSingleNode(String nodeName) {
        return Objects.requireNonNull(getNode(nodeName)).item(0);
    }
}
