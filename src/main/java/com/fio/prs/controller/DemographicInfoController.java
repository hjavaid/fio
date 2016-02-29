package com.fio.prs.controller;

import com.fio.prs.dto.DemographicDTO;
import com.fio.prs.model.DemographicInfo;
import com.fio.prs.repo.DemographicInfoRepository;
import com.fio.prs.service.DemographicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by haris on 25/02/16.
 */
@RestController
@RequestMapping("/demographics")
public class DemographicInfoController {
    @Autowired
    private DemographicInfoRepository repo;

    @Autowired
    private DemographicsService demographicsService;

    @RequestMapping(method = RequestMethod.GET)
    public List findDemographics()
    {
        return repo.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public DemographicInfo findDemographic(@PathVariable Integer id) {
        return repo.findOne(id);
    }


    @RequestMapping(method = RequestMethod.POST,path = "/create", consumes = "application/json")
    public DemographicInfo createDemographics(@RequestBody DemographicDTO demographicDTO) throws IOException {
       if(demographicDTO == null){
           return null;
       }

       final DemographicInfo retVal = demographicsService.createDemographics(demographicDTO.getDocument(),demographicDTO.getDemographicInfo());
        return retVal;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public DemographicInfo updateDemographicInfo(@RequestBody DemographicDTO demographicDTO, @PathVariable Integer id) {
       return demographicsService.updateDemographicInfo(demographicDTO,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDemographicInfo(@PathVariable Integer id) {
        repo.delete(id);
    }

}
