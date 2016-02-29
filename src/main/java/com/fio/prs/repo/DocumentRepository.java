package com.fio.prs.repo;

import com.fio.prs.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haris on 25/02/16.
 */
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Document  findByExternalId(String externalId);
}
