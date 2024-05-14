package com.batch.model.TVLFile;

import java.util.HashMap;
import java.util.Map;
public class TVLHeader {
    private String submissionDateTime;
    private Integer sSIOPHubID;
    private String homeAgencyID;
    private String submissionType;
    private Integer bulkIdentifier;
    private String bulkIndicator;
    private Integer recordCount;
    public String getSubmissionDateTime() {
        return submissionDateTime;
    }
    public void setSubmissionDateTime(String submissionDateTime) {
        this.submissionDateTime = submissionDateTime;
    }
    public Integer getSSIOPHubID() {
        return sSIOPHubID;
    }
    public void setSSIOPHubID(Integer sSIOPHubID) {
        this.sSIOPHubID = sSIOPHubID;
    }
    public String getHomeAgencyID() {
        return homeAgencyID;
    }
    public void setHomeAgencyID(String homeAgencyID) {
        this.homeAgencyID = homeAgencyID;
    }
    public String getSubmissionType() {
        return submissionType;
    }
    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }
    public Integer getBulkIdentifier() {
        return bulkIdentifier;
    }
    public void setBulkIdentifier(Integer bulkIdentifier) {
        this.bulkIdentifier = bulkIdentifier;
    }
    public String getBulkIndicator() {
        return bulkIndicator;
    }
    public void setBulkIndicator(String bulkIndicator) {
        this.bulkIndicator = bulkIndicator;
    }
    public Integer getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }
}


