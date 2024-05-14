package com.batch.reader;

import com.batch.constants.BatchConstants;
import com.batch.model.ITAGFileData;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class ITAGFileReader {


    public ItemReader<ITAGFileData> reader() {
        FlatFileItemReader<ITAGFileData> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("C:\\Users\\ashis\\data\\ofc_docs\\BRISKWIN\\project_2024\\sample_files\\033_20240414200239_ITAG\\033_20240414200239.ITAG"));
        reader.setLineMapper(lineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    public LineMapper<ITAGFileData> lineMapper() {
        DefaultLineMapper<ITAGFileData> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(fixedLengthTokenizer());
        lineMapper.setFieldSetMapper(fieldSetMapper());
        return lineMapper;
    }

    public FixedLengthTokenizer fixedLengthTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setNames("TAG_AGENCY_ID", "TAG_SERIAL_NUMBER", "TAG_STATUS", "TAG_ACCT_INFO");
        tokenizer.setColumns(new Range(1,3), new Range(4,11), new Range(12,12), new Range(13,18));
        return tokenizer;
    }

    public FieldSetMapper<ITAGFileData> fieldSetMapper() {
        return fieldSet -> new ITAGFileData(
                fieldSet.readString("TAG_AGENCY_ID"),
                fieldSet.readString("TAG_SERIAL_NUMBER"),
                fieldSet.readString("TAG_STATUS"),
                fieldSet.readString("TAG_ACCT_INFO")
        );
    }

}
