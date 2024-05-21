package com.batch.writer;

import jakarta.xml.bind.JAXBContext;
import org.codehaus.stax2.XMLStreamWriter2;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;

@Component
public class TvlXmlGenerator {

    private XMLStreamWriter2 xmlWriter;
    private JAXBContext jaxbContext;
    private StringWriter sw;

    private RandomAccessFile raf ;
    private FileWriter fileWriter ;


    public void setXmlWriter(XMLStreamWriter2 xmlWriter) {
        this.xmlWriter = xmlWriter;
    }

    public XMLStreamWriter2 getXmlWriter(){
        return this.xmlWriter;
    }


    public StringWriter getSw() {
        return sw;
    }

    public void setSw(StringWriter sw) {
        this.sw = sw;
    }


    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public void setJaxbContext(JAXBContext jaxbContext) {
        this.jaxbContext = jaxbContext;
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

    public void setRaf(RandomAccessFile raf) {
        this.raf = raf;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }
}
