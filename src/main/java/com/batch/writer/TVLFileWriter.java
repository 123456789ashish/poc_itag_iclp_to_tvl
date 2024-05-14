package com.batch.writer;

import com.batch.model.TVLTableData;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;

@Component
public class TVLFileWriter implements ItemWriter<TVLTableData> {



    @Override
    public void write(Chunk<? extends TVLTableData> chunk) throws Exception {

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(
                new FileOutputStream("C:\\Users\\ashis\\data\\ofc_docs\\BRISKWIN\\project_2024\\workspace\\ITAG_ICLP_TO_BTVL\\src\\main\\resources\\output\\test.xml"));




    }


}