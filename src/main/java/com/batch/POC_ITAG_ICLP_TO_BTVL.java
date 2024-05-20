package com.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@EnableBatchProcessing
public class POC_ITAG_ICLP_TO_BTVL {

	public static void main(String[] args) {
		SpringApplication.run(POC_ITAG_ICLP_TO_BTVL.class, args);
	}

}
