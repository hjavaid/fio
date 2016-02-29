package com.fio.prs.repo;

import com.fio.prs.model.DemographicInfo;
import com.fio.prs.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haris on 25/02/16.
 */
public interface DemographicInfoRepository extends JpaRepository<DemographicInfo, Integer> {
    DemographicInfo findByDocument(Document document);
}
