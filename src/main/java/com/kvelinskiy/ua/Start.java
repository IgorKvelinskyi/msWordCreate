package com.kvelinskiy.ua;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Igor Kvelinskyi (igorkvjava@gmail.com)
 */
public class Start {
    public static void main(String[] args) throws JAXBException, IOException, Docx4JException {
        List<List<String>> dataTable = new ArrayList<>();
        List<String> lineTable1 = Arrays.asList("1", "3", "Писарук Марина Генадіївна", "07.02.1984р.", "м.Київ,Святошинський р-н,вул.С.Сосніних,б.12,кв.22",
                "18.11.2019р.визнана недіезд.", "Куковець Тетяна Миколаївна", "м.Київ,Святошинський р-н,вул.С.Сосніних буд.12,кв.22");
        for (int i = 0; i < 15; i++) {
            dataTable.add(lineTable1);
        }
        DomEditXML domEditXML = new DomEditXML();
        domEditXML.changeDataFileXMLListOfIncapacitated(dataTable);
        ReadXmlFile readXmlFile = new ReadXmlFile();
        readXmlFile.saveDocumentWord("outputIncapacitated.xml", "Incapacitated.docx");

    }
}
