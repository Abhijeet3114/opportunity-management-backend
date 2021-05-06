package com.OpportunityManagement.backend.repository;

import com.OpportunityManagement.backend.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuditRepository extends JpaRepository<Audit, Long> {


}
