package com.batch.listener;

import com.batch.model.TVLFile.TVLTagDetail;
import com.batch.writer.TvlXmlGenerator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamWriter2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamException;
import java.io.*;

@Component
public class TvlStepExecutionListener implements StepExecutionListener {

    @Autowired
    private TvlXmlGenerator tvlXmlGenerator;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        try {
            RandomAccessFile raf = new RandomAccessFile("C:\\Users\\ashis\\data\\ofc_docs\\BRISKWIN\\project_2024\\workspace\\generated_files\\MINI_5T_50C.xml","rw");
            FileWriter fileWriter = new FileWriter(raf.getFD());

            XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory2.newFactory();
            //xmlOutputFactory.setProperty("escapeCharacters", false);

            XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(fileWriter);

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");

            xmlStreamWriter.writeRaw("\n");
            xmlStreamWriter.writeStartElement("TagValidationList");
            xmlStreamWriter.writeRaw("\n");

            // TODO - add header details here

            // setting writer bean
            tvlXmlGenerator.setXmlWriter(xmlStreamWriter);

            // setting raf and fileWriter
            tvlXmlGenerator.setRaf(raf);
            tvlXmlGenerator.setFileWriter(fileWriter);



            // setting jaxb context in bean
            JAXBContext jaxbContext = JAXBContext.newInstance(TVLTagDetail.class);

            tvlXmlGenerator.setJaxbContext(jaxbContext);

            // setting writer
            StringWriter sw = new StringWriter();
            tvlXmlGenerator.setSw(sw);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Step started at: " + stepExecution.getStartTime());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        try {
            XMLStreamWriter2 xmlStreamWriter = tvlXmlGenerator.getXmlWriter();

            xmlStreamWriter.writeRaw("\n");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

            //closing filewriter and raf
            RandomAccessFile raf=tvlXmlGenerator.getRaf( );
            FileWriter fileWriter=tvlXmlGenerator.getFileWriter();

            fileWriter.close();
            raf.close();


            System.out.println(stepExecution.getReadCount());
            System.out.println(stepExecution.getWriteCount());
            System.out.println(stepExecution.getSkipCount());
            System.out.println(stepExecution.getFilterCount());
            System.out.println(stepExecution.getRollbackCount());
            System.out.println(stepExecution.getReadSkipCount());
            System.out.println(stepExecution.getCommitCount());
            System.out.println(stepExecution.getWriteSkipCount());


        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Step finished at: " + stepExecution.getEndTime());
        return null; // Return null to use the default ExitStatus
    }
}