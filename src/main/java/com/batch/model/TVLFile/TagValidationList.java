package com.batch.model.TVLFile;

import java.util.HashMap;
import java.util.Map;
public class TagValidationList {
    private TVLDetail tVLDetail;
    private TVLHeader tVLHeader;
    public TVLDetail getTVLDetail() {
        return tVLDetail;
    }
    public void setTVLDetail(TVLDetail tVLDetail) {
        this.tVLDetail = tVLDetail;
    }
    public TVLHeader getTVLHeader() {
        return tVLHeader;
    }
    public void setTVLHeader(TVLHeader tVLHeader) {
        this.tVLHeader = tVLHeader;
    }
}