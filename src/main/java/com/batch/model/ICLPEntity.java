package com.batch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ICLPEntity {


    @Id
    private Integer RECORD_ID;
    private String LIC_STATE;
    private String LIC_NUMBER;
    private String LIC_TYPE;
    private String TAG_AGENCY_ID;
    private String TAG_SERIAL_NUMBER;

}
