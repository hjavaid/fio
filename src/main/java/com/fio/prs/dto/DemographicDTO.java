package com.fio.prs.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fio.prs.model.DemographicInfo;
import com.fio.prs.model.Document;

/**
 * Created by haris on 27/02/16.
 */
public class DemographicDTO {

    private DemographicInfo demographicInfo;
    private Document document;

    @JsonCreator
    public DemographicDTO(@JsonProperty("demographicInfo") DemographicInfo demographicInfo, @JsonProperty("document") Document document){
        this.demographicInfo = demographicInfo;
        this.document = document;
    }

    public DemographicInfo getDemographicInfo() {
        return demographicInfo;
    }

    public void setDemographicInfo(DemographicInfo demographicInfo) {
        this.demographicInfo = demographicInfo;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
