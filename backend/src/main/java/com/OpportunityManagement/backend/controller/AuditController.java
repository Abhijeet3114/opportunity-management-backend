package com.OpportunityManagement.backend.controller;

import com.OpportunityManagement.backend.model.Audit;
import com.OpportunityManagement.backend.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuditController {
    @Autowired
    AuditRepository auditRepository;

    @GetMapping("/opportunities/audit/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Audit> getOpportunities(){
        return auditRepository.findAll();
    }
}
