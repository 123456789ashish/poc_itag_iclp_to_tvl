package com.batch.processor;

import com.batch.model.ICLPEntity;
import com.batch.model.ICLPFileData;
import com.batch.model.ITAGEntity;
import org.springframework.batch.item.ItemProcessor;

public class ICLPFileToEntityProcessor implements ItemProcessor<ICLPFileData, ICLPEntity> {
    @Override
    public ICLPEntity process(ICLPFileData fileRecord) {
        ICLPEntity iclpEntity = new ICLPEntity();
        iclpEntity.setLIC_STATE(fileRecord.LIC_STATE());
        iclpEntity.setLIC_NUMBER(fileRecord.LIC_NUMBER());
        iclpEntity.setLIC_TYPE(fileRecord.LIC_TYPE());
        iclpEntity.setTAG_AGENCY_ID(fileRecord.TAG_AGENCY_ID());
        iclpEntity.setTAG_SERIAL_NUMBER(fileRecord.TAG_SERIAL_NUMBER());

        return iclpEntity;
    }
}
