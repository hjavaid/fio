package com.fio.prs.service;

import com.fio.prs.dto.DocumentDTO;
import com.fio.prs.model.Document;
import com.fio.prs.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haris on 28/02/16.
 */
@Service
public class DocumentCrossReferenceService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> createDocumentReference(final Map<String,String> documentReferences){
        if(documentReferences == null ){
            throw new IllegalArgumentException("Document reference map is null");
        }

        final List<Document> docs = new ArrayList<>();

        for (final Map.Entry<String,String> entry: documentReferences.entrySet()) {
            final Document doc = new Document();
            doc.setIssuer(entry.getValue());
            doc.setExternalId(entry.getKey());
            doc.setId(null);
            docs.add(documentRepository.saveAndFlush(doc));
        }

        return docs;
    }

    public List getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document findOne(Integer id) {
        return documentRepository.findOne(id);
    }

    public List<Document> createDocuments(DocumentDTO documentList) throws IOException {
        if(documentList == null){
            return null;
        }
        final Map<String,String> documentReferenceMap = new HashMap<>();
        for (Document document:documentList.getDocumentList()) {
            documentReferenceMap.put(document.getExternalId(),document.getIssuer());
        }
        final List<Document> retVal = createDocumentReference(documentReferenceMap);
        return retVal;
    }

    public Document updateDocument(DocumentDTO documentDTO, Integer id) {
        final Document doc = documentRepository.findOne(id);
        final Document source = documentDTO.getDocumentList().get(0); //bad, but in a rush!
        if(doc != null) {
            doc.setIssuer(source.getIssuer());
            doc.setExternalId(source.getExternalId());

            return documentRepository.saveAndFlush(doc);
        }else{
            return null;
        }
    }

    public void delete(Integer id) {
        documentRepository.delete(id);
    }
}
