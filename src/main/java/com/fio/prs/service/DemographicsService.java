package com.fio.prs.service;

import com.fio.prs.dto.DemographicDTO;
import com.fio.prs.model.DemographicInfo;
import com.fio.prs.model.Document;
import com.fio.prs.repo.DemographicInfoRepository;
import com.fio.prs.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haris on 28/02/16.
 */
@Service
public class DemographicsService {
    @Autowired
    private DemographicInfoRepository demographicInfoRepository;
    @Autowired
    private DocumentRepository documentRepository;

    public DemographicInfo createDemographics(final Document document, final DemographicInfo demographicsInfo){
        if(document == null || demographicsInfo == null){
            throw new IllegalArgumentException("Missing and/orIllegal values provided, bailing out....");
        }


        final Document existingDoc = documentRepository.findByExternalId(document.getExternalId());
        DemographicInfo newDemographincsInfo;
        Document doc = null;

        if(existingDoc == null) {
            final Document newDoc = new Document();
            newDoc.setIssuer(document.getIssuer());
            newDoc.setExternalId(document.getExternalId());
            doc = documentRepository.saveAndFlush(newDoc);

            newDemographincsInfo = new DemographicInfo();
            newDemographincsInfo.setFirstName(demographicsInfo.getFirstName());
            newDemographincsInfo.setLastName(demographicsInfo.getLastName());
            newDemographincsInfo.setAge(demographicsInfo.getAge());
            newDemographincsInfo.setDateOfBirth(demographicsInfo.getDateOfBirth());
            newDemographincsInfo.setId(null);
            newDemographincsInfo.setDocument(doc);
        }else{
            newDemographincsInfo = demographicInfoRepository.findByDocument(existingDoc);
            newDemographincsInfo.setDocument(existingDoc);
        }

        DemographicInfo demographicInfo = demographicInfoRepository.saveAndFlush(newDemographincsInfo);

        return demographicInfo;

    }

    public List getAllDemographics() {
       return demographicInfoRepository.findAll();
    }

    public DemographicInfo findOne(Integer id) {
        return demographicInfoRepository.findOne(id);
    }

    public DemographicInfo updateDemographicInfo(DemographicDTO demographicDTO, Integer id) {
        final DemographicInfo di = demographicInfoRepository.findOne(id);
        if(di != null) {
            di.setAge(demographicDTO.getDemographicInfo().getAge());
            di.setDateOfBirth(demographicDTO.getDemographicInfo().getDateOfBirth());
            di.setFirstName(demographicDTO.getDemographicInfo().getFirstName());
            di.setLastName(demographicDTO.getDemographicInfo().getLastName());
            demographicDTO.setDocument(demographicDTO.getDocument());
            return demographicInfoRepository.saveAndFlush(di);
        }else{
            return null;
        }
    }

    public void delete(Integer id) {
        demographicInfoRepository.delete(id);
    }
}
