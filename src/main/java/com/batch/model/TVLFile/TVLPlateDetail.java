package com.batch.model.TVLFile;


import java.util.ArrayList;
import java.util.List;

public class TVLPlateDetail {
    private List<TVLPlateDetails> tVLPlateDetails = new ArrayList<TVLPlateDetails>();
    public List<TVLPlateDetails> getTVLPlateDetails() {
        return tVLPlateDetails;
    }
    public void setTVLPlateDetails(List<TVLPlateDetails> tVLPlateDetails) {
        this.tVLPlateDetails = tVLPlateDetails;
    }
}
