package com.kvelinskiy.ua;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * @author Igor Kvelinskyi (igorkvjava@gmail.com)
 */
public class Start {
    public static void main(String[] args) throws JAXBException, IOException, Docx4JException {
        System.out.println("fbgfbgf");
        DomEditXML domEditXML = new DomEditXML();
        domEditXML.changeDataFileXMLListOfIncapacitated("listOfIncapacitatedHead.xml",
                "listOfIncapacitatedBody.xml", "listOfIncapacitatedFooter.xml", "outputIncapacitated.xml");
        ReadXmlFile readXmlFile = new ReadXmlFile();
        readXmlFile.saveDocumentWord("outputIncapacitated.xml", "Incapacitated.docx");

    }
}
