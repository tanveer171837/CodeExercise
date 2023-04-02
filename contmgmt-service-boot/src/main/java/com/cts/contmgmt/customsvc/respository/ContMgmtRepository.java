package com.cts.contmgmt.customsvc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.contmgmt.customsvc.entity.Document;

@Repository
public interface ContMgmtRepository extends JpaRepository<Document, Long>{

}
