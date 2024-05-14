package com.batch.config;

import com.batch.model.ITAGEntity;
import com.batch.model.ITAGFileData;
import com.batch.processor.ITAGFileToEntityProcessor;
import com.batch.reader.ITAGFileReader;
import com.batch.writer.ITAGRepository;
import com.batch.writer.ITAGWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ITAGBatchConfig {


    @Bean
    public ItemReader<ITAGFileData> itagReader() {
        return new ITAGFileReader().reader();
    }

    @Bean
    public ITAGFileToEntityProcessor itagProcessor() {
        return new ITAGFileToEntityProcessor();
    }

    @Bean
    public RepositoryItemWriter<ITAGEntity> itagWriter() {
        return new ITAGWriter().itagWriter();
    }

    @Bean
    public TaskExecutor itagTaskExecutor() {

       /* ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(15);
        taskExecutor.setQueueCapacity(10);*/

        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

    @Bean
    public Step itagFileIngestionsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("itag-file-ingestion-step", jobRepository)
                .<ITAGFileData, ITAGEntity>chunk(20000, transactionManager)
                .reader(itagReader())
                .processor(itagProcessor())
                .writer(itagWriter())
                .taskExecutor(itagTaskExecutor())
                .build();
    }



    @Bean(name="itagRunJob")
    public Job itagRunJob( JobRepository jobRepository,  Step itagFileIngestionsStep) {
        return new JobBuilder("itagFileIngestionJob", jobRepository)
                .start(itagFileIngestionsStep)
                .build();

    }
}
