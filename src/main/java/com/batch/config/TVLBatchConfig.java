package com.batch.config;

import com.batch.listener.TvlStepExecutionListener;
import com.batch.reader.TVLTableReader;
import com.batch.writer.TVLFileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class TVLBatchConfig {

    @Autowired
    private TvlStepExecutionListener tvlStepExecutionListener;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TVLFileWriter tvlFileWriter;

    @Bean
    public JdbcPagingItemReader<String> tvlTableDataItemReader() throws Exception {
        return new TVLTableReader().jdbcPagingItemReader(dataSource );
    }

   /* @Bean
    public TvlProcessor tvlProcessor() {
        return new TvlProcessor();
    }*/


   /* public StaxEventItemWriter<TVLEntity> tvlFileWriter() throws Exception {
        return new TVLFileWriter().customerItemWriter();
    }*/



    @Bean
    public TaskExecutor tvlTaskExecutor() {

      /*  ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(15);
        taskExecutor.setQueueCapacity(10);*/


        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        return asyncTaskExecutor;
    }

    @Bean
    public Step tvlFileCreationStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("tvl-file-creation-step", jobRepository)
                .<String, String>chunk(50, transactionManager)
                .reader(tvlTableDataItemReader())
                //.processor(tvlProcessor())
                .writer(tvlFileWriter)
                .listener(tvlStepExecutionListener)
                .taskExecutor(tvlTaskExecutor())
                .build();
    }



    @Bean(name="tvlRunJob")
    public Job tvlRunJob( JobRepository jobRepository,  Step tvlFileCreationStep) {
        return new JobBuilder("tvlFileCreationJob", jobRepository)
                .start(tvlFileCreationStep)
                .build();

    }
}
