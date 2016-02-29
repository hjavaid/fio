package com.fio.prs.controller;

import com.fio.prs.dto.DemographicDTO;
import com.fio.prs.dto.DocumentDTO;
import com.fio.prs.model.DemographicInfo;
import com.fio.prs.model.Document;
import com.fio.prs.service.DemographicsService;
import com.fio.prs.service.DocumentCrossReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by haris on 25/02/16.
 */
@RestController
@RequestMapping("/prs")
public class PrsController {
    @Autowired
    private DemographicsService demographicsService;
    @Autowired
    private DocumentCrossReferenceService documentCrossReferenceService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public List findDemographics()
    {
        return demographicsService.getAllDemographics();
    }

    @RequestMapping(value = "/documents",method = RequestMethod.GET)
    public List findDocuments()
    {
        return documentCrossReferenceService.getAllDocuments();
    }

    @RequestMapping(value = "/register/{id}",method = RequestMethod.GET)
    public DemographicInfo findDemographic(@PathVariable Integer id)
    {
        return demographicsService.findOne(id);
    }

    @RequestMapping(value = "/document/{id}",method = RequestMethod.GET)
    public Document findDocument(@PathVariable Integer id)
    {
        return documentCrossReferenceService.findOne(id);
    }


    @RequestMapping(method = RequestMethod.POST,path = "/register/create", consumes = "application/json")
    public DemographicInfo createDemographics(@RequestBody DemographicDTO demographicDTO) throws IOException {
       if(demographicDTO == null){
           return null;
       }

       final DemographicInfo retVal = demographicsService.createDemographics(demographicDTO.getDocument(),demographicDTO.getDemographicInfo());
        return retVal;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/documents/create", consumes = "application/json")
    public List<Document> createDocuments(@RequestBody DocumentDTO documentList) throws IOException {
        return documentCrossReferenceService.createDocuments(documentList);
    }

    @RequestMapping(value = "/register/{id}", method = RequestMethod.PUT)
    public DemographicInfo updateDemographicInfo(@RequestBody DemographicDTO demographicDTO, @PathVariable Integer id) {
        return demographicsService.updateDemographicInfo(demographicDTO, id);
    }

    @RequestMapping(value = "/document/{id}", method = RequestMethod.PUT)
    public Document updateDocument(@RequestBody DocumentDTO documentDTO, @PathVariable Integer id) {
        if(documentDTO == null){
            return null;
        }
        return documentCrossReferenceService.updateDocument(documentDTO, id);
    }

    @RequestMapping(value = "/register/{id}", method = RequestMethod.DELETE)
    public void deleteDemographicInfo(@PathVariable Integer id) {
        demographicsService.delete(id);
    }


    @RequestMapping(value = "/document/{id}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable Integer id) {
        documentCrossReferenceService.delete(id);
    }

}
