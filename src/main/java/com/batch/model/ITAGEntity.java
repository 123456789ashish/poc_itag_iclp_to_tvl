package com.batch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ITAG_RECORDS")
public class ITAGEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer RECORD_ID;
    private String TAG_AGENCY_ID;
    private String TAG_SERIAL_NUMBER;
    private String TAG_STATUS;
    private String TAG_ACCT_INFO;

}
