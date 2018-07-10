package studygroup.deoksunlee.thinkalarmbot.parser;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Parser4XmlTest {
    private static final Logger logger = LoggerFactory.getLogger(Parser4XmlTest.class);

    @Test
    public void parse() throws Exception {
        // given
        String xml = getSimpleXML();

        // when
        String xpathExpression = "//row/col1";
        String actual = Parser4Xml.parse(xml, xpathExpression);
        logger.debug("actual : " + actual);

        // then
        String expected = "값1";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseList() {
        // given
        String xml = getSampleXML();

        // when
        String xpathExpression = "//entrydata[@columnnumber=12]/text";
        List<String> actual = Parser4Xml.parseList(xml, xpathExpression);
        logger.debug("actual : " + actual);

        // then
        List<String> expected = Arrays.asList("3867", "3864", "3862", "3861", "3845");
        Assert.assertEquals(expected, actual);
    }

    static List<Event> getEventList() {
        return Arrays.asList(
                new Event("3867", "[임직원EVENT]***인터파크VR 임직원 EVENT !!***"),
                new Event("3864", "★땡처리★ 남프랑스+크로아티아 5국10일 [A380탑승]"),
                new Event("3862", "[미세먼지대란] 톡딜 한정수량 특가 샤오미 공기청정기 79,900원 (4/6)"),
                new Event("3861", "●썬키스트 블랙라벨 오렌지 20과 한정판매●"),
                new Event("3845", "♥4월 동유럽 특가♥봄맞이♥ 동유럽 패키지 1,240,000원!!!"));
    }

    @Test
    public void parseToEvnetList() {
        // given
        String xml = getSampleXMLAfterTrim();

        // when
        Events events = Parser4Xml.parseToEvents(xml);
        List<Event> actual = events.getEventList();
        logger.debug("actual : " + actual);

        // then
        List<Event> expected = getEventList();
        Assert.assertEquals(expected.get(0).getEventId(), actual.get(0).getEventId());
        Assert.assertEquals(expected.get(1).getEventId(), actual.get(1).getEventId());
        Assert.assertEquals(expected.get(2).getEventId(), actual.get(2).getEventId());
        Assert.assertEquals(expected.get(3).getEventId(), actual.get(3).getEventId());
        Assert.assertEquals(expected.get(4).getEventId(), actual.get(4).getEventId());
    }

    @Test
    public void parse_sampleXML() throws Exception {
        // given
        String xml = getSampleXML();

        // when
        String xpathExpression = "//entrydata[@columnnumber=12]/text";
        String actual = Parser4Xml.parse(xml, xpathExpression);
        logger.debug("actual : " + actual);

        // then
        String expected = "3867";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parse_sampleXML_title() throws Exception {
        // given
        String xml = getSampleXML();

        // when
        String xpathExpression = "//entrydata[@columnnumber=3]/text";
        String actual = Parser4Xml.parse(xml, xpathExpression);
        logger.debug("actual : " + actual);

        // then
        String expected = "[임직원EVENT]***인터파크VR 임직원 EVENT !!***";
        Assert.assertEquals(expected, actual);
    }

    private String getSimpleXML() {
        return "<root><row><col1 id='c1'>값1</col1><col2 id='c2' val='val2'>값2</col2></row>" +
                "<row><col1 id='c3'>값3</col1><col2 id='c4'>값4</col2></row></root>";
    }

    private String getSampleXML() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<viewentries timestamp=\"20180406T044918,63Z\" toplevelentries=\"760\">\n" +
                "    <viewentry position=\"1\" unid=\"3DC918CFA055CA3E4925826600196231\" noteid=\"6BDDA\" siblings=\"760\">\n" +
                "        <entrydata columnnumber=\"0\" name=\"DocLevel\">\n" +
                "            <textlist>\n" +
                "                <text>2</text>\n" +
                "            </textlist>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"1\" name=\"게시일\">\n" +
                "            <text>2018-04-05</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"2\" name=\"AuthorName\">\n" +
                "            <text>정승원</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"3\" name=\"$13\">\n" +
                "        <text>[임직원EVENT]***인터파크VR 임직원 EVENT !!***</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"4\" name=\"종료일\">\n" +
                "            <text>-</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"5\" name=\"첨부\">\n" +
                "            <text>1</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"6\" name=\"조회\">\n" +
                "            <text>1197</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"7\" name=\"$8\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"8\" name=\"$9\">\n" +
                "            <text>[1]</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"9\" name=\"$11\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"10\" name=\"$15\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"11\" name=\"AuthorName3\">\n" +
                "            <text>담당</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"12\" name=\"$17\">\n" +
                "            <text>3867</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"13\" name=\"$20\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"14\" name=\"$19\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"15\" name=\"AuthorName_E\">\n" +
                "            <text>Jeong Seung Won</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"16\" name=\"AuthorName3_E\">\n" +
                "            <text>담당</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\">\n" +
                "            <text>인터파크씨어터</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"18\" name=\"$23\">\n" +
                "            <text>인터파크씨어터</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"19\" name=\"Subject_header\">\n" +
                "            <text>임직원EVENT</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\">\n" +
                "            <text>P0000</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"21\" name=\"$22\">\n" +
                "            <text>임직원EVENT(e)</text>\n" +
                "        </entrydata>\n" +
                "    </viewentry>\n" +
                "    <viewentry position=\"2\" unid=\"92C0D5C02421240749258265002A26F6\" noteid=\"6BD5A\" siblings=\"760\">\n" +
                "        <entrydata columnnumber=\"0\" name=\"DocLevel\">\n" +
                "            <textlist>\n" +
                "                <text>2</text>\n" +
                "            </textlist>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"1\" name=\"게시일\">\n" +
                "            <text>2018-04-04</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"2\" name=\"AuthorName\">\n" +
                "            <text>최지은</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"3\" name=\"$13\">\n" +
                "            <text>★땡처리★ 남프랑스+크로아티아 5국10일 [A380탑승]</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"4\" name=\"종료일\">\n" +
                "            <text>-</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"5\" name=\"첨부\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"6\" name=\"조회\">\n" +
                "            <text>408</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"7\" name=\"$8\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"8\" name=\"$9\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"9\" name=\"$11\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"10\" name=\"$15\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"11\" name=\"AuthorName3\">\n" +
                "            <text>대리</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"12\" name=\"$17\">\n" +
                "            <text>3864</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"13\" name=\"$20\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"14\" name=\"$19\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"15\" name=\"AuthorName_E\">\n" +
                "            <text>Choi Jieun</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"16\" name=\"AuthorName3_E\">\n" +
                "            <text>대리</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\">\n" +
                "            <text>투어부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"18\" name=\"$23\">\n" +
                "            <text>투어부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"19\" name=\"Subject_header\">\n" +
                "            <text>임직원EVENT</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\">\n" +
                "            <text>N8888</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"21\" name=\"$22\">\n" +
                "            <text>임직원EVENT(e)</text>\n" +
                "        </entrydata>\n" +
                "    </viewentry>\n" +
                "    <viewentry position=\"3\" unid=\"EE30338D7EB6C9DC492582650019D05B\" noteid=\"6BCE2\" siblings=\"760\">\n" +
                "        <entrydata columnnumber=\"0\" name=\"DocLevel\">\n" +
                "            <textlist>\n" +
                "                <text>2</text>\n" +
                "            </textlist>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"1\" name=\"게시일\">\n" +
                "            <text>2018-04-04</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"2\" name=\"AuthorName\">\n" +
                "            <text>백준</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"3\" name=\"$13\">\n" +
                "            <text>[미세먼지대란] 톡딜 한정수량 특가 샤오미 공기청정기 79,900원 (4/6)</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"4\" name=\"종료일\">\n" +
                "            <text>-</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"5\" name=\"첨부\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"6\" name=\"조회\">\n" +
                "            <text>1178</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"7\" name=\"$8\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"8\" name=\"$9\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"9\" name=\"$11\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"10\" name=\"$15\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"11\" name=\"AuthorName3\">\n" +
                "            <text>대리</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"12\" name=\"$17\">\n" +
                "            <text>3862</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"13\" name=\"$20\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"14\" name=\"$19\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"15\" name=\"AuthorName_E\">\n" +
                "            <text>Back Jun</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"16\" name=\"AuthorName3_E\">\n" +
                "            <text>대리</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\">\n" +
                "            <text>쇼핑부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"18\" name=\"$23\">\n" +
                "            <text>쇼핑부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"19\" name=\"Subject_header\">\n" +
                "            <text>none</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\">\n" +
                "            <text>N8888</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"21\" name=\"$22\">\n" +
                "            <text>none</text>\n" +
                "        </entrydata>\n" +
                "    </viewentry>\n" +
                "    <viewentry position=\"4\" unid=\"C7D9274075C3EE8D49258265000C3C40\" noteid=\"6BCCE\" siblings=\"760\">\n" +
                "        <entrydata columnnumber=\"0\" name=\"DocLevel\">\n" +
                "            <textlist>\n" +
                "                <text>2</text>\n" +
                "            </textlist>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"1\" name=\"게시일\">\n" +
                "            <text>2018-04-04</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"2\" name=\"AuthorName\">\n" +
                "            <text>김병회</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"3\" name=\"$13\">\n" +
                "            <text>●썬키스트 블랙라벨 오렌지 20과 한정판매●</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"4\" name=\"종료일\">\n" +
                "            <text>-</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"5\" name=\"첨부\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"6\" name=\"조회\">\n" +
                "            <text>1144</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"7\" name=\"$8\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"8\" name=\"$9\">\n" +
                "            <text>[1]</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"9\" name=\"$11\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"10\" name=\"$15\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"11\" name=\"AuthorName3\">\n" +
                "            <text>사원</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"12\" name=\"$17\">\n" +
                "            <text>3861</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"13\" name=\"$20\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"14\" name=\"$19\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"15\" name=\"AuthorName_E\">\n" +
                "            <text>Kim ByungHoi</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"16\" name=\"AuthorName3_E\">\n" +
                "            <text>사원</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\">\n" +
                "            <text>쇼핑부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"18\" name=\"$23\">\n" +
                "            <text>쇼핑부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"19\" name=\"Subject_header\">\n" +
                "            <text>임직원EVENT</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\">\n" +
                "            <text>N8888</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"21\" name=\"$22\">\n" +
                "            <text>임직원EVENT(e)</text>\n" +
                "        </entrydata>\n" +
                "    </viewentry>\n" +
                "    <viewentry position=\"5\" unid=\"E23D9392D4CD446E4925825D001E5415\" noteid=\"6B132\" siblings=\"760\">\n" +
                "        <entrydata columnnumber=\"0\" name=\"DocLevel\">\n" +
                "            <textlist>\n" +
                "                <text>2</text>\n" +
                "            </textlist>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"1\" name=\"게시일\">\n" +
                "            <text>2018-03-27</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"2\" name=\"AuthorName\">\n" +
                "            <text>이선희</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"3\" name=\"$13\">\n" +
                "            <text>♥4월 동유럽 특가♥봄맞이♥ 동유럽 패키지 1,240,000원!!!</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"4\" name=\"종료일\">\n" +
                "            <text>-</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"5\" name=\"첨부\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"6\" name=\"조회\">\n" +
                "            <text>657</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"7\" name=\"$8\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"8\" name=\"$9\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"9\" name=\"$11\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"10\" name=\"$15\">\n" +
                "            <text>0</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"11\" name=\"AuthorName3\">\n" +
                "            <text>대리</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"12\" name=\"$17\">\n" +
                "            <text>3845</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"13\" name=\"$20\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"14\" name=\"$19\">\n" +
                "            <text></text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"15\" name=\"AuthorName_E\">\n" +
                "            <text>LEE, SUNHEE</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"16\" name=\"AuthorName3_E\">\n" +
                "            <text>대리</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\">\n" +
                "            <text>투어부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"18\" name=\"$23\">\n" +
                "            <text>투어부문</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"19\" name=\"Subject_header\">\n" +
                "            <text>임직원EVENT</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\">\n" +
                "            <text>N8888</text>\n" +
                "        </entrydata>\n" +
                "        <entrydata columnnumber=\"21\" name=\"$22\">\n" +
                "            <text>임직원EVENT(e)</text>\n" +
                "        </entrydata>\n" +
                "    </viewentry>\n" +
                "</viewentries>\n";
    }

    private String getSampleXMLAfterTrim() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<viewentries timestamp=\"20180406T044918,63Z\" toplevelentries=\"760\">" +
                "<viewentry position=\"1\" unid=\"3DC918CFA055CA3E4925826600196231\" noteid=\"6BDDA\" siblings=\"760\"><entrydata columnnumber=\"0\" name=\"DocLevel\"><textlist><text>2</text></textlist></entrydata><entrydata columnnumber=\"1\" name=\"게시일\"><text>2018-04-05</text></entrydata><entrydata columnnumber=\"2\" name=\"AuthorName\"><text>정승원</text></entrydata><entrydata columnnumber=\"3\" name=\"$13\"><text>[임직원EVENT]***인터파크VR 임직원 EVENT !!***</text></entrydata><entrydata columnnumber=\"4\" name=\"종료일\"><text>-</text></entrydata><entrydata columnnumber=\"5\" name=\"첨부\"><text>1</text></entrydata><entrydata columnnumber=\"6\" name=\"조회\"><text>1197</text></entrydata><entrydata columnnumber=\"7\" name=\"$8\"><text></text></entrydata><entrydata columnnumber=\"8\" name=\"$9\"><text>[1]</text></entrydata><entrydata columnnumber=\"9\" name=\"$11\"><text>0</text></entrydata><entrydata columnnumber=\"10\" name=\"$15\"><text>0</text></entrydata><entrydata columnnumber=\"11\" name=\"AuthorName3\"><text>담당</text></entrydata><entrydata columnnumber=\"12\" name=\"$17\"><text>3867</text></entrydata><entrydata columnnumber=\"13\" name=\"$20\"><text></text></entrydata><entrydata columnnumber=\"14\" name=\"$19\"><text></text></entrydata><entrydata columnnumber=\"15\" name=\"AuthorName_E\"><text>Jeong Seung Won</text></entrydata><entrydata columnnumber=\"16\" name=\"AuthorName3_E\"><text>담당</text></entrydata><entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\"><text>인터파크씨어터</text></entrydata><entrydata columnnumber=\"18\" name=\"$23\"><text>인터파크씨어터</text></entrydata><entrydata columnnumber=\"19\" name=\"Subject_header\"><text>임직원EVENT</text></entrydata><entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\"><text>P0000</text></entrydata><entrydata columnnumber=\"21\" name=\"$22\"><text>임직원EVENT(e)</text></entrydata></viewentry>" +
                "<viewentry position=\"2\" unid=\"92C0D5C02421240749258265002A26F6\" noteid=\"6BD5A\" siblings=\"760\"><entrydata columnnumber=\"0\" name=\"DocLevel\"><textlist><text>2</text></textlist></entrydata><entrydata columnnumber=\"1\" name=\"게시일\"><text>2018-04-04</text></entrydata><entrydata columnnumber=\"2\" name=\"AuthorName\"><text>최지은</text></entrydata><entrydata columnnumber=\"3\" name=\"$13\"><text>★땡처리★ 남프랑스+크로아티아 5국10일 [A380탑승]</text></entrydata><entrydata columnnumber=\"4\" name=\"종료일\"><text>-</text></entrydata><entrydata columnnumber=\"5\" name=\"첨부\"><text>0</text></entrydata><entrydata columnnumber=\"6\" name=\"조회\"><text>408</text></entrydata><entrydata columnnumber=\"7\" name=\"$8\"><text></text></entrydata><entrydata columnnumber=\"8\" name=\"$9\"><text></text></entrydata><entrydata columnnumber=\"9\" name=\"$11\"><text>0</text></entrydata><entrydata columnnumber=\"10\" name=\"$15\"><text>0</text></entrydata><entrydata columnnumber=\"11\" name=\"AuthorName3\"><text>대리</text></entrydata><entrydata columnnumber=\"12\" name=\"$17\"><text>3864</text></entrydata><entrydata columnnumber=\"13\" name=\"$20\"><text></text></entrydata><entrydata columnnumber=\"14\" name=\"$19\"><text></text></entrydata><entrydata columnnumber=\"15\" name=\"AuthorName_E\"><text>Choi Jieun</text></entrydata><entrydata columnnumber=\"16\" name=\"AuthorName3_E\"><text>대리</text></entrydata><entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\"><text>투어부문</text></entrydata><entrydata columnnumber=\"18\" name=\"$23\"><text>투어부문</text></entrydata><entrydata columnnumber=\"19\" name=\"Subject_header\"><text>임직원EVENT</text></entrydata><entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\"><text>N8888</text></entrydata><entrydata columnnumber=\"21\" name=\"$22\"><text>임직원EVENT(e)</text></entrydata></viewentry>" +
                "<viewentry position=\"3\" unid=\"EE30338D7EB6C9DC492582650019D05B\" noteid=\"6BCE2\" siblings=\"760\"><entrydata columnnumber=\"0\" name=\"DocLevel\"><textlist><text>2</text></textlist></entrydata><entrydata columnnumber=\"1\" name=\"게시일\"><text>2018-04-04</text></entrydata><entrydata columnnumber=\"2\" name=\"AuthorName\"><text>백준</text></entrydata><entrydata columnnumber=\"3\" name=\"$13\"><text>[미세먼지대란] 톡딜 한정수량 특가 샤오미 공기청정기 79,900원 (4/6)</text></entrydata><entrydata columnnumber=\"4\" name=\"종료일\"><text>-</text></entrydata><entrydata columnnumber=\"5\" name=\"첨부\"><text>0</text></entrydata><entrydata columnnumber=\"6\" name=\"조회\"><text>1178</text></entrydata><entrydata columnnumber=\"7\" name=\"$8\"><text></text></entrydata><entrydata columnnumber=\"8\" name=\"$9\"><text></text></entrydata><entrydata columnnumber=\"9\" name=\"$11\"><text>0</text></entrydata><entrydata columnnumber=\"10\" name=\"$15\"><text>0</text></entrydata><entrydata columnnumber=\"11\" name=\"AuthorName3\"><text>대리</text></entrydata><entrydata columnnumber=\"12\" name=\"$17\"><text>3862</text></entrydata><entrydata columnnumber=\"13\" name=\"$20\"><text></text></entrydata><entrydata columnnumber=\"14\" name=\"$19\"><text></text></entrydata><entrydata columnnumber=\"15\" name=\"AuthorName_E\"><text>Back Jun</text></entrydata><entrydata columnnumber=\"16\" name=\"AuthorName3_E\"><text>대리</text></entrydata><entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\"><text>쇼핑부문</text></entrydata><entrydata columnnumber=\"18\" name=\"$23\"><text>쇼핑부문</text></entrydata><entrydata columnnumber=\"19\" name=\"Subject_header\"><text>none</text></entrydata><entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\"><text>N8888</text></entrydata><entrydata columnnumber=\"21\" name=\"$22\"><text>none</text></entrydata></viewentry>" +
                "<viewentry position=\"4\" unid=\"C7D9274075C3EE8D49258265000C3C40\" noteid=\"6BCCE\" siblings=\"760\"><entrydata columnnumber=\"0\" name=\"DocLevel\"><textlist><text>2</text></textlist></entrydata><entrydata columnnumber=\"1\" name=\"게시일\"><text>2018-04-04</text></entrydata><entrydata columnnumber=\"2\" name=\"AuthorName\"><text>김병회</text></entrydata><entrydata columnnumber=\"3\" name=\"$13\"><text>●썬키스트 블랙라벨 오렌지 20과 한정판매●</text></entrydata><entrydata columnnumber=\"4\" name=\"종료일\"><text>-</text></entrydata><entrydata columnnumber=\"5\" name=\"첨부\"><text>0</text></entrydata><entrydata columnnumber=\"6\" name=\"조회\"><text>1144</text></entrydata><entrydata columnnumber=\"7\" name=\"$8\"><text></text></entrydata><entrydata columnnumber=\"8\" name=\"$9\"><text>[1]</text></entrydata><entrydata columnnumber=\"9\" name=\"$11\"><text>0</text></entrydata><entrydata columnnumber=\"10\" name=\"$15\"><text>0</text></entrydata><entrydata columnnumber=\"11\" name=\"AuthorName3\"><text>사원</text></entrydata><entrydata columnnumber=\"12\" name=\"$17\"><text>3861</text></entrydata><entrydata columnnumber=\"13\" name=\"$20\"><text></text></entrydata><entrydata columnnumber=\"14\" name=\"$19\"><text></text></entrydata><entrydata columnnumber=\"15\" name=\"AuthorName_E\"><text>Kim ByungHoi</text></entrydata><entrydata columnnumber=\"16\" name=\"AuthorName3_E\"><text>사원</text></entrydata><entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\"><text>쇼핑부문</text></entrydata><entrydata columnnumber=\"18\" name=\"$23\"><text>쇼핑부문</text></entrydata><entrydata columnnumber=\"19\" name=\"Subject_header\"><text>임직원EVENT</text></entrydata><entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\"><text>N8888</text></entrydata><entrydata columnnumber=\"21\" name=\"$22\"><text>임직원EVENT(e)</text></entrydata></viewentry>" +
                "<viewentry position=\"5\" unid=\"E23D9392D4CD446E4925825D001E5415\" noteid=\"6B132\" siblings=\"760\"><entrydata columnnumber=\"0\" name=\"DocLevel\"><textlist><text>2</text></textlist></entrydata><entrydata columnnumber=\"1\" name=\"게시일\"><text>2018-03-27</text></entrydata><entrydata columnnumber=\"2\" name=\"AuthorName\"><text>이선희</text></entrydata><entrydata columnnumber=\"3\" name=\"$13\"><text>♥4월 동유럽 특가♥봄맞이♥ 동유럽 패키지 1,240,000원!!!</text></entrydata><entrydata columnnumber=\"4\" name=\"종료일\"><text>-</text></entrydata><entrydata columnnumber=\"5\" name=\"첨부\"><text>0</text></entrydata><entrydata columnnumber=\"6\" name=\"조회\"><text>657</text></entrydata><entrydata columnnumber=\"7\" name=\"$8\"><text></text></entrydata><entrydata columnnumber=\"8\" name=\"$9\"><text></text></entrydata><entrydata columnnumber=\"9\" name=\"$11\"><text>0</text></entrydata><entrydata columnnumber=\"10\" name=\"$15\"><text>0</text></entrydata><entrydata columnnumber=\"11\" name=\"AuthorName3\"><text>대리</text></entrydata><entrydata columnnumber=\"12\" name=\"$17\"><text>3845</text></entrydata><entrydata columnnumber=\"13\" name=\"$20\"><text></text></entrydata><entrydata columnnumber=\"14\" name=\"$19\"><text></text></entrydata><entrydata columnnumber=\"15\" name=\"AuthorName_E\"><text>LEE, SUNHEE</text></entrydata><entrydata columnnumber=\"16\" name=\"AuthorName3_E\"><text>대리</text></entrydata><entrydata columnnumber=\"17\" name=\"AuthorBonOrgName\"><text>투어부문</text></entrydata><entrydata columnnumber=\"18\" name=\"$23\"><text>투어부문</text></entrydata><entrydata columnnumber=\"19\" name=\"Subject_header\"><text>임직원EVENT</text></entrydata><entrydata columnnumber=\"20\" name=\"AuthorHeadOrgCode\"><text>N8888</text></entrydata><entrydata columnnumber=\"21\" name=\"$22\"><text>임직원EVENT(e)</text></entrydata></viewentry>" +
                "</viewentries>";
    }
}
