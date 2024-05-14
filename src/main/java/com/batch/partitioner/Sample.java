package com.batch.partitioner;


import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.TransformerConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class Sample {


    public static void main(String[] args) throws IOException, XMLStreamException, TransformerConfigurationException {


        Writer fileWriter = new FileWriter("C:\\Users\\ashis\\data\\ofc_docs\\BRISKWIN\\project_2024\\workspace\\ITAG_ICLP_TO_BTVL\\src\\main\\resources\\output\\test_7.xml");

        // Getting the XMLOutputFactory instance
        XMLOutputFactory xmlOutputFactory  = XMLOutputFactory.newInstance();
        xmlOutputFactory.setProperty("escapeCharacters", false);

        // Creating XMLStreamWriter object from
        // xmlOutputFactory.
        //XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);

        // for indention
        XMLStreamWriter xmlStreamWriter = new IndentingXMLStreamWriter(xmlOutputFactory.createXMLStreamWriter(fileWriter));

        xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
        xmlStreamWriter.writeStartElement("TagValidationList");

        System.out.println(new Date());
        int flushCount=0;

        for(int i=0 ; i < 100; i++) {
            xmlStreamWriter.writeCharacters("<myTag>");
            xmlStreamWriter.writeCharacters("<tag1>"+i+"</tag1>");
            xmlStreamWriter.writeCharacters("<tag2>"+i+"</tag2>");
            xmlStreamWriter.writeCharacters("<tag3>"+i+"</tag3>");
            xmlStreamWriter.writeCharacters("<tag4>"+i+"</tag4>");
            xmlStreamWriter.writeCharacters("<tag5>"+i+"</tag5>");
            xmlStreamWriter.writeCharacters("</myTag>");
            flushCount++;

            if(flushCount == 1000000 ){
                xmlStreamWriter.flush();
                flushCount = 0;
            }
        }

        System.out.println(new Date());

        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.flush();
        xmlStreamWriter.close();


    }

}
