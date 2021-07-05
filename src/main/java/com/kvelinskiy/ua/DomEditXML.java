package com.kvelinskiy.ua;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Igor Kvelinskyi (igorkvjava@gmail.com)
 */
public class DomEditXML {
    public void changeDataFileXMLListOfIncapacitated(List<List<String>> dataTable){
        try {
            File fXmlFileInputXMLHead = new File("listOfIncapacitatedHead.xml");
            File fXmlFileInputXMLBody = new File("listOfIncapacitatedBody.xml");
            File fXmlFileInputXMLFooter = new File("listOfIncapacitatedFooter.xml");
            String dataHead = contentFileRecording(fXmlFileInputXMLHead);
            String dataBody = contentFileRecording(fXmlFileInputXMLBody);
            DataProcessing dataProcessing = new DataProcessing();
            dataBody= dataProcessing.changeData(dataTable, dataBody);
            String dataFooter = contentFileRecording(fXmlFileInputXMLFooter);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File input = writingContentFile(dataHead+dataBody+dataFooter, "fileInputXML.xml");
            Document docInputXML = dBuilder.parse(input);
            docInputXML.getDocumentElement().normalize();
            writeDocument(docInputXML, "outputIncapacitated.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String contentFileRecording(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Scanner scan = new Scanner(fileReader);
        StringBuffer strBuffer = new StringBuffer();
        while (scan.hasNext()){
            strBuffer.append(scan.nextLine());
            strBuffer.append("\n");
        }
        fileReader.close();
        return strBuffer.toString();
    }

    private File writingContentFile(String content, String fileOutputXML) throws IOException {
        FileOutputStream fos=new FileOutputStream(fileOutputXML);
        byte[] buffer = content.getBytes();
        fos.write(buffer, 0, buffer.length);
        File file = new File(fileOutputXML);
        return file;
    }

    public void changeDataFileXML(String fileInputXML, String fileOutputXML) {
        // create a new DocumentBuilderFactory
        try {
            File fXmlFile = new File(fileInputXML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            changeData(doc, fileOutputXML);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void changeData(Document doc, String fileOutputXML) {
        // Получаем корневой элемент
        System.out.print("Root element :" + doc.getDocumentElement().getNodeName() + "----");
        NodeList nList = doc.getElementsByTagName("w:r");
        System.out.println(nList.getLength());
        int count = 0;
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                //changeTagContent(eElement, "0");
                System.out.println("w:t : " + eElement.getElementsByTagName("w:t").item(0).getTextContent() + "\nTag number - " + i);
            }
        }
        writeDocument(doc, fileOutputXML);
    }

    // write DOM to file
    private void writeDocument(Document document, String fileOutputXML) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(fileOutputXML);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void changeTagContent(Element element, String content) {
        element.getElementsByTagName("w:t").item(0).setTextContent(content);
    }
}
