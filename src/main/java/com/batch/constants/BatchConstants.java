package com.batch.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BatchConstants {

    @Value("file.input.itag")
    public static String ITAG_FILE_INPUT;
}
