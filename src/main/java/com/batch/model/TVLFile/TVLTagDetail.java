package com.batch.model.TVLFile;

import java.util.HashMap;
import java.util.Map;
public class TVLTagDetail {
    private String tagAgencyID;
    private TVLAccountDetails tVLAccountDetails;
    private String homeAgencyID;
    private String tagStatus;
    private TVLPlateDetail tVLPlateDetail;
    private Integer tagClass;
    private Integer tagSerialNumber;
    public String getTagAgencyID() {
        return tagAgencyID;
    }
    public void setTagAgencyID(String tagAgencyID) {
        this.tagAgencyID = tagAgencyID;
    }
    public TVLAccountDetails getTVLAccountDetails() {
        return tVLAccountDetails;
    }
    public void setTVLAccountDetails(TVLAccountDetails tVLAccountDetails) {
        this.tVLAccountDetails = tVLAccountDetails;
    }
    public String getHomeAgencyID() {
        return homeAgencyID;
    }
    public void setHomeAgencyID(String homeAgencyID) {
        this.homeAgencyID = homeAgencyID;
    }
    public String getTagStatus() {
        return tagStatus;
    }
    public void setTagStatus(String tagStatus) {
        this.tagStatus = tagStatus;
    }
    public TVLPlateDetail getTVLPlateDetail() {
        return tVLPlateDetail;
    }
    public void setTVLPlateDetail(TVLPlateDetail tVLPlateDetail) {
        this.tVLPlateDetail = tVLPlateDetail;
    }
    public Integer getTagClass() {
        return tagClass;
    }
    public void setTagClass(Integer tagClass) {
        this.tagClass = tagClass;
    }
    public Integer getTagSerialNumber() {
        return tagSerialNumber;
    }
    public void setTagSerialNumber(Integer tagSerialNumber) {
        this.tagSerialNumber = tagSerialNumber;
    }
}

