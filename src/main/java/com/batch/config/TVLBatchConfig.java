package com.batch.config;

import com.batch.model.TVLTableData;
import com.batch.reader.TVLTableReader;
import com.batch.writer.TVLFileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class TVLBatchConfig {


    @Bean
    public ItemReader<TVLTableData> tvlTableDataItemReader() {
        return new TVLTableReader().customerPagingItemReader();
    }

    @Autowired
    private TVLFileWriter tvlFileWriter;



    @Bean
    public TaskExecutor tvlTaskExecutor() {

       /* ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(15);
        taskExecutor.setQueueCapacity(10);*/

        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

    @Bean
    public Step tvlTableIngestionsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("tvl-table-ingestion-step", jobRepository)
                .<TVLTableData, TVLTableData>chunk(20000, transactionManager)
                .reader(tvlTableDataItemReader())
                .writer(tvlFileWriter)
                .taskExecutor(tvlTaskExecutor())
                .build();
    }



    @Bean(name="tvlRunJob")
    public Job tvlRunJob( JobRepository jobRepository,  Step tvlTableIngestionsStep) {
        return new JobBuilder("tvlTableIngestionJob", jobRepository)
                .start(tvlTableIngestionsStep)
                .build();

    }
}
