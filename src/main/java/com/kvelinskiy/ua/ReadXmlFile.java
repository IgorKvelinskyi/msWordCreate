package com.kvelinskiy.ua;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Igor Kvelinskyi (igorkvjava@gmail.com)
 */
public class ReadXmlFile {
    static final Logger logger = LoggerFactory.getLogger(ReadXmlFile.class);
//    logger.debug("Did it again!");  <--- insert method
    /*public void saveDocumentWord() throws IOException {
        File xmlFile = new File("1.xml");

        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line = bufReader.readLine();

        Writer out = new FileWriter("outFile.docx");

        while (line != null) {
            sb.append(line).append("\n");
            line = bufReader.readLine();
            if (line != null)
                out.write(line);
        }
        out.close();
        fileReader.close();

        String xml2String = sb.toString();
        System.out.println(xml2String);
    }*/

    public void saveDocumentWord(String fileInputXML, String fileOutPutDOCX) throws IOException, JAXBException, Docx4JException {
        File xmlFile = new File(fileInputXML);
        byte[] data;
        try (FileInputStream fis = new FileInputStream(xmlFile)) {
            data = new byte[(int) xmlFile.length()];
            fis.read(data);
        }
        String openXML1 =new String(data, "UTF-8");
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
        mdp.setContents(dRectangleViaXML(openXML1));
        wordMLPackage.save(new File(fileOutPutDOCX));
    }

    private Document dRectangleViaXML(String openXML) throws JAXBException {
        return (Document) XmlUtils.unmarshalString(openXML);
    }
}
