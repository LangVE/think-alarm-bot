package studygroup.deoksunlee.thinkalarmbot.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Parser4Xml {

    public static final String EVENT_ID = "$17";
    public static final String EVENT_TITLE = "$13";

    public static String parse(String xml, String xpathExpression) {
        try {

            // XML Document 객체 생성
            InputSource is = new InputSource(new StringReader(xml));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            // xpath 생성
            XPath xpath = XPathFactory.newInstance().newXPath();

            // NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
            NodeList cols = (NodeList) xpath.evaluate(xpathExpression, document, XPathConstants.NODESET);

            String result = cols.item(0).getTextContent();

            return result;
        } catch (Exception e) {
            String message = String.format("XML 파싱중 오류 발생(%s)", xml);
            throw new RuntimeException(message, e);
        }
    }

    public static List<String> parseList(String xml, String xpathExpression) {
        try {

            // XML Document 객체 생성
            InputSource is = new InputSource(new StringReader(xml));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            // xpath 생성
            XPath xpath = XPathFactory.newInstance().newXPath();

            // NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
            NodeList cols = (NodeList) xpath.evaluate(xpathExpression, document, XPathConstants.NODESET);

            return getList(cols);
        } catch (Exception e) {
            String message = String.format("XML 파싱중 오류 발생(%s)", xml);
            throw new RuntimeException(message, e);
        }
    }

    private static List<String> getList(NodeList cols) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cols.getLength(); i++) {
            list.add(cols.item(i).getTextContent());
        }
        return list;
    }

    public static List<Event> parseToEventList(String xml, String xpathExpression) {
        try {

            // XML Document 객체 생성
            InputSource is = new InputSource(new StringReader(xml));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            // xpath 생성
            XPath xpath = XPathFactory.newInstance().newXPath();

            // NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
            NodeList cols = (NodeList) xpath.evaluate(xpathExpression, document, XPathConstants.NODESET);

            return getEventList(cols);
        } catch (Exception e) {
            String message = String.format("XML 파싱중 오류 발생(%s)", xml);
            throw new RuntimeException(message, e);
        }
    }

    private static List<Event> getEventList(NodeList cols) {
        List<Event> result = new ArrayList<>();

        for (int i = 0; i < cols.getLength(); i++) {
            NodeList entrydataNodes = cols.item(i).getChildNodes();
            result.add(getEvent(entrydataNodes));
        }

        return result;
    }

    private static Event getEvent(NodeList entrydataNodes) {
        String eventId = "";
        String eventTitle = "";

        for (int j = 0; j < entrydataNodes.getLength(); j++) {
            if (entrydataNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                if (entrydataNodes.item(j).getAttributes().getNamedItem("name").getNodeValue().equals(EVENT_ID)) {
                    eventId = entrydataNodes.item(j).getTextContent();
                }
                if (entrydataNodes.item(j).getAttributes().getNamedItem("name").getNodeValue().equals(EVENT_TITLE)) {
                    eventTitle = entrydataNodes.item(j).getTextContent();
                }
            }
        }
        return new Event(eventId, eventTitle);
    }
}
