package com.fio.prs.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fio.prs.model.Document;

import java.util.List;

/**
 * Created by haris on 27/02/16.
 */
public class DocumentDTO {

    private List<Document> documentList;

    @JsonCreator
    public DocumentDTO( @JsonProperty("documentList") List<Document> documentList){
        this.documentList = documentList;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
}
