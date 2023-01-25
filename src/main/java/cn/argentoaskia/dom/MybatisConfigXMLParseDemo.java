package cn.argentoaskia.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class MybatisConfigXMLParseDemo {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // 1. 读入XML文件到流，File对象或者URL等都ok
        InputStream mybatisConfigXml = MybatisConfigXMLParseDemo.class.getResourceAsStream("/mybatis-config.xml");

        // 2. 通过DocumentBuilderFactory.newInstance()获取DocumentBuilderFactory对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // 3. 通过documentBuilderFactory.newDocumentBuilder()来创建DocumentBuilder对象
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        // 4.通过documentBuilder.parse()来解析XML输入流、XML File对象或者XML文件URL等
        //   也可以使用newDocument()创建一个空的XML文档。
        Document mybatisConfigDocument = documentBuilder.parse(mybatisConfigXml);

        // 5.一个Document对象就代表一个XML文件，DOM方式的解析会把XML文件解析成一个数（Tree）的结构
        // 一般情况下，一个复杂的XML至少包含下面的部分：DocumentType、EntityReference、Element、Attributes、ProcessingInstruction
        // Comment、Text、CDATA、Entity等
        NamedNodeMap attributes = mybatisConfigDocument.getAttributes();
        String baseURI = mybatisConfigDocument.getBaseURI();
        NodeList childNodes = mybatisConfigDocument.getChildNodes();
        DocumentType doctype = mybatisConfigDocument.getDoctype();
        Element documentElement = mybatisConfigDocument.getDocumentElement();
        String documentURI = mybatisConfigDocument.getDocumentURI();
        DOMConfiguration domConfig = mybatisConfigDocument.getDomConfig();
        Element insert = mybatisConfigDocument.getElementById("insert");
        NodeList select = mybatisConfigDocument.getElementsByTagName("select");
        NodeList sql = mybatisConfigDocument.getElementsByTagName("sql");
        NodeList elementsByTagNameNS = mybatisConfigDocument.getElementsByTagNameNS("*", "*");
        Node firstChild = mybatisConfigDocument.getFirstChild();
        DOMImplementation implementation = mybatisConfigDocument.getImplementation();
        String inputEncoding = mybatisConfigDocument.getInputEncoding();
        Node lastChild = mybatisConfigDocument.getLastChild();
        String localName = mybatisConfigDocument.getLocalName();
        String namespaceURI = mybatisConfigDocument.getNamespaceURI();
        Node nextSibling = mybatisConfigDocument.getNextSibling();
        String nodeName = mybatisConfigDocument.getNodeName();
        short nodeType = mybatisConfigDocument.getNodeType();
        String nodeValue = mybatisConfigDocument.getNodeValue();
        Document ownerDocument = mybatisConfigDocument.getOwnerDocument();
        Node parentNode = mybatisConfigDocument.getParentNode();
        String prefix = mybatisConfigDocument.getPrefix();
        Node previousSibling = mybatisConfigDocument.getPreviousSibling();
        boolean strictErrorChecking = mybatisConfigDocument.getStrictErrorChecking();
        String textContent = mybatisConfigDocument.getTextContent();
        Object userData = mybatisConfigDocument.getUserData("123");
        String xmlEncoding = mybatisConfigDocument.getXmlEncoding();
        boolean xmlStandalone = mybatisConfigDocument.getXmlStandalone();
        String xmlVersion = mybatisConfigDocument.getXmlVersion();

        mybatisConfigXml.close();
    }
}
