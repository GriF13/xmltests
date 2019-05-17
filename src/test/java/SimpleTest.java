import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SimpleTest {

    @Test
    public void test1() throws IOException, SAXException, ParserConfigurationException {
        FileClass.ggetp("xml_test.xml");
        Main main = new Main();
        main.actions();
    }

    @Test
    public void test2() throws IOException, SAXException, ParserConfigurationException {
        FileClass.ggetp("xml_test2.xml");
        Main main = new Main();
        main.actions();
    }

    @Test
    public void test3() throws IOException, SAXException, ParserConfigurationException {
        FileClass.ggetp("xml_test3.xml");
        Main main = new Main();
        main.actions();
    }
}
