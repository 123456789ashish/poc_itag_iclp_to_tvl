package com.batch.model.TVLFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TVLDetail {
    private List<TVLTagDetail> tVLTagDetails = new ArrayList<TVLTagDetail>();
    public List<TVLTagDetail> getTVLTagDetails() {
        return tVLTagDetails;
    }
    public void setTVLTagDetails(List<TVLTagDetail> tVLTagDetails) {
        this.tVLTagDetails = tVLTagDetails;
    }
}

