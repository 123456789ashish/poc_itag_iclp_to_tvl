package com.batch.mapper;

import com.batch.model.ICLPFileData;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ICLPFileDataMapper implements FieldSetMapper<ICLPFileData> {

    @Override
    public ICLPFileData mapFieldSet(FieldSet fieldSet) throws BindException {
        if (fieldSet == null) {
            return null;
        }
        return new ICLPFileData(
                fieldSet.readString("LIC_STATE"),
                fieldSet.readString("LIC_NUMBER"),
                fieldSet.readString("LIC_TYPE"),
                fieldSet.readString("TAG_AGENCY_ID"),
                fieldSet.readString("TAG_SERIAL_NUMBER")
        );
    }
}