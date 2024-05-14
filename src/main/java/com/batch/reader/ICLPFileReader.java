package com.batch.reader;

import com.batch.model.ICLPFileData;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.core.io.FileSystemResource;

public class ICLPFileReader {


    public ItemReader<ICLPFileData> reader() {
        FlatFileItemReader<ICLPFileData> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("C:\\Users\\ashis\\data\\ofc_docs\\BRISKWIN\\project_2024\\sample_files\\033_20240414214851_ICLP\\033_20240414214851.ICLP"));
        reader.setLineMapper(lineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    public LineMapper<ICLPFileData> lineMapper() {
        DefaultLineMapper<ICLPFileData> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(fixedLengthTokenizer());
        lineMapper.setFieldSetMapper(fieldSetMapper());
        return lineMapper;
    }

    public FixedLengthTokenizer fixedLengthTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setNames("LIC_STATE", "LIC_NUMBER" , "LIC_TYPE" , "TAG_AGENCY_ID", "TAG_SERIAL_NUMBER");
        tokenizer.setColumns(new Range(1,2), new Range(3,12), new Range(13,14), new Range(15,17) , new Range(18,25));
        return tokenizer;
    }

    public FieldSetMapper<ICLPFileData> fieldSetMapper() {
        return fieldSet -> new ICLPFileData(
                fieldSet.readString("LIC_STATE"),
                fieldSet.readString("LIC_NUMBER"),
                fieldSet.readString("LIC_TYPE"),
                fieldSet.readString("TAG_AGENCY_ID"),
                fieldSet.readString("TAG_SERIAL_NUMBER")
        );
    }

}
