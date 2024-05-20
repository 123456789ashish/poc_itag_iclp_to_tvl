package com.batch.model.TVLFile;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name="TVLTagDetail")
public class TVLTagDetail {

    private String tagAgencyID;
    private String tagSerialNumber;
    private String tagStatus;
    private String tagAccountInfo;

    private TVLPlateDetail tvlPlateDetail;

    public String getTagAccountInfo() {
        return tagAccountInfo;
    }

    public void setTagAccountInfo(String tagAccountInfo) {
        this.tagAccountInfo = tagAccountInfo;
    }

    public TVLPlateDetail gettVLPlateDetail() {
        return tvlPlateDetail;
    }

    public void settVLPlateDetail(TVLPlateDetail tVLPlateDetail) {
        this.tvlPlateDetail = tVLPlateDetail;
    }

    public String getTagAgencyID() {
        return tagAgencyID;
    }

    public void setTagAgencyID(String tagAgencyID) {
        this.tagAgencyID = tagAgencyID;
    }

    public String getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(String tagStatus) {
        this.tagStatus = tagStatus;
    }

    public String getTagSerialNumber() {
        return tagSerialNumber;
    }

    public void setTagSerialNumber(String tagSerialNumber) {
        this.tagSerialNumber = tagSerialNumber;
    }
}

