package com.fio.prs.controller;

import com.fio.prs.dto.DocumentDTO;
import com.fio.prs.model.Document;
import com.fio.prs.repo.DocumentRepository;
import com.fio.prs.service.DocumentCrossReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by haris on 25/02/16.
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentRepository repo;

    @Autowired
    private DocumentCrossReferenceService documentCrossReferenceService;

    @RequestMapping(method = RequestMethod.GET)
    public List findDocuments() {
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Document addDocument(@RequestBody Document document) {
        document.setId(null);
        return repo.saveAndFlush(document);
    }
    @RequestMapping(method = RequestMethod.POST,path = "/create", consumes = "application/json")
    public List<Document> createDocuments(@RequestBody DocumentDTO documentList) throws IOException {
        return documentCrossReferenceService.createDocuments(documentList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Document updateDocument(@RequestBody Document document, @PathVariable Integer id) {
        document.setId(id);
        return repo.saveAndFlush(document);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable Integer id) {
        repo.delete(id);
    }

}
