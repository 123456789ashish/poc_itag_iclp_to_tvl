package com.batch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ITAGEntity {

    @Id
    private Integer RECORD_ID;
    private String TAG_AGENCY_ID;
    private String TAG_SERIAL_NUMBER;
    private String TAG_STATUS;
    private String TAG_ACCT_INFO;

}
