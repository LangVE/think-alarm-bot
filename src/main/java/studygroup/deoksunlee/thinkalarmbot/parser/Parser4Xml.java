package studygroup.deoksunlee.thinkalarmbot.parser;

import org.w3c.dom.Document;
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
}
