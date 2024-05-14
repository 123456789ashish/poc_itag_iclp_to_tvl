package com.batch.writer;

import com.batch.model.ITAGEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class ITAGWriter  {


    @Autowired
    private ITAGRepository repository;

    public RepositoryItemWriter<ITAGEntity> itagWriter() {
        RepositoryItemWriter<ITAGEntity> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }
}
