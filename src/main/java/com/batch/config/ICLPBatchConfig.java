package com.batch.config;

import com.batch.model.ICLPEntity;
import com.batch.model.ICLPFileData;
import com.batch.processor.ICLPFileToEntityProcessor;
import com.batch.reader.ICLPFileReader;
import com.batch.writer.ICLPRepository;
import com.batch.writer.ICLPWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ICLPBatchConfig {

    @Autowired
    private ICLPWriter iCLPWriter;

    @Bean
    public ItemReader<ICLPFileData> iclpReader() {
        return new ICLPFileReader().reader();
    }

    @Bean
    public ICLPFileToEntityProcessor iclpProcessor() {
        return new ICLPFileToEntityProcessor();
    }

    @Bean
    public RepositoryItemWriter<ICLPEntity> iclpWriter() {
        return iCLPWriter.iclpWriter();
    }

    @Bean
    public TaskExecutor iclpTaskExecutor() {

       /* ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(15);
        taskExecutor.setQueueCapacity(10);*/

        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

    // DB to file ( try on - partition - if we have a coulmn - range coulmn )



    @Bean
    public Step iclpFileIngestionsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("iclp-file-ingestion-step", jobRepository)
                .<ICLPFileData, ICLPEntity>chunk(20000, transactionManager)
                .reader(iclpReader())
                .processor(iclpProcessor())
                .writer(iclpWriter())
                .taskExecutor(iclpTaskExecutor())
                .build();
    }



    @Bean(name="iclpRunJob")
    public Job iclpRunJob( JobRepository jobRepository,  Step iclpFileIngestionsStep) {
        return new JobBuilder("iclpFileIngestionJob", jobRepository)
                .start(iclpFileIngestionsStep)
                .build();

    }
}
