package com.batch.model;


import lombok.Builder;

@Builder
public record TVLTableData(String TAG_AGENCY_ID, String TAG_SERIAL_NUMBER, String TAG_STATUS, String TAG_ACCT_INFO , String LIC_STATE,  String LIC_NUMBER, String LIC_TYPE ) {

}
