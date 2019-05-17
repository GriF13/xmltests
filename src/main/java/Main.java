import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private static void ByPassTreeSimple(Node node, ElementClass el) {
        if (node.hasChildNodes()) {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    ElementClass child = el.addChild(list.item(i).getNodeName());
                    ByPassTreeSimple(list.item(i), child);
                }
            }
        }
    }

    private static void ByPassTreeHard(Node node, ElementTextClass el) {
        if (node.hasChildNodes()) {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    String val = "";
                    if (list.item(i).hasChildNodes()) {
                        NodeList sublist = list.item(i).getChildNodes();
                        for (int j = 0; j < sublist.getLength(); j++) {
                            if (sublist.item(j).getNodeType() == Node.TEXT_NODE)
                                val += sublist.item(j).getNodeValue();
                        }
                        if (val == "" || val.matches("[' ''\n']+"))
                            val = null;
                    } else val = null;
                    ElementTextClass child = el.addChild(list.item(i).getNodeName(), val);
                    ByPassTreeHard(list.item(i), child);
                }
            }
        }
    }

    public void actions() throws ParserConfigurationException, SAXException, IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            String newS = FileClass.ggetp(FileClass.bb);
            Document doc = builder.parse(new File("resourse/" + newS));
            Element root = doc.getDocumentElement();

            System.out.println(newS + " correct");

            ElementClass el1 = new ElementClass(root.getNodeName());
            ByPassTreeSimple(root, el1);

            ElementTextClass el2 = new ElementTextClass(root.getNodeName(), null);
            ByPassTreeHard(root, el2);

            ObjectMapper objectMapper1 = new ObjectMapper();
            objectMapper1.writer().withDefaultPrettyPrinter().writeValue(new File("example1.json"), el1);

            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.writer().withDefaultPrettyPrinter().writeValue(new File("example2.json"), el2);


        } catch (Exception jse) {
            System.out.println("Wrong XML");
        }

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        new Main().actions();
    }
}
