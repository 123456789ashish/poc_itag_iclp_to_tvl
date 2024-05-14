package com.batch.writer;

import com.batch.model.ICLPEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class ICLPWriter  {

    @Autowired
    private ICLPRepository repository;


    public RepositoryItemWriter<ICLPEntity> iclpWriter() {
        RepositoryItemWriter<ICLPEntity> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }
}
