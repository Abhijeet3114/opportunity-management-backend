package com.OpportunityManagement.backend.controller;

import com.OpportunityManagement.backend.exception.ResourceNotFoundException;
import com.OpportunityManagement.backend.model.Audit;
import com.OpportunityManagement.backend.model.Opportunity;
import com.OpportunityManagement.backend.repository.AuditRepository;
import com.OpportunityManagement.backend.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:8080")

public class OpportunityController {
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private AuditRepository auditRepository;

    //get valid Opportunities
    @GetMapping("/opportunities")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Opportunity> getOpportunities(){
        return opportunityRepository.getValidOpportunity();
    }

    //add opportunity
    @PostMapping("/opportunities") //post method to save opportunity details
    @CrossOrigin(origins = "http://localhost:4200")
    public Opportunity addOpportunity( @RequestBody Opportunity opportunity){ //@reuestbody used because post method needs json format
        //Opportunity opportunity1 = opportunityRepository.save(opportunity);
        Audit audit = new Audit();
        audit.setOpportunityId(opportunity.getOpportunityId());
        audit.setAction("CREATED");
        audit.setRole(opportunity.getRole());
        audit.setModifiedBy(opportunity.getHiringManager());
        audit.setLocation(opportunity.getJoiningLocation());
        audit.setCreatedOn(opportunity.getPublishDate());
        audit.setModifiedOn(opportunity.getPublishDate());
        auditRepository.save(audit);
        return opportunityRepository.save(opportunity);
    }

    //get opportunity by ID
    @GetMapping("/opportunities/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Opportunity> getById(@PathVariable Long id){
        Opportunity opportunity = opportunityRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Opportunity not exist"));
        return ResponseEntity.ok(opportunity);
    }

    //Search with role,hiringmanager, location, skills
    @GetMapping("/opportunities/search/{role}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Opportunity>> getOpportunityByRole(@PathVariable("role") String role){
        List<Opportunity> opportunity = opportunityRepository.getOppotunityByRole(role);
        return ResponseEntity.ok(opportunity);
    }

    //find all
    @GetMapping("/opportunities/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Opportunity> getAllOpportunities(){
        return opportunityRepository.findAll(Sort.by("publishDate").descending());
    }
    //update opportunity
    @DeleteMapping("/opportunities/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable Long id){
        Opportunity opportunity = opportunityRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Opportunity not exist"));

        String opportunity_INSHORT = opportunity.getRole()+" opportunity created by "+opportunity.getHiringManager()+" on "+
                opportunity.getPublishDate()+" is successfully deleted";

        Audit audit = new Audit();
        Date modifiedOn = new Date();
        audit.setOpportunityId(opportunity.getOpportunityId());
        audit.setAction("DELETED");
        audit.setRole(opportunity.getRole());
        audit.setLocation(opportunity.getJoiningLocation());
        audit.setModifiedBy(opportunity.getHiringManager());
        audit.setCreatedOn(opportunity.getPublishDate());
        audit.setModifiedOn(modifiedOn);
        auditRepository.save(audit);
        opportunityRepository.delete(opportunity);
        Map<String , Boolean> response = new HashMap<>();
        response.put(opportunity_INSHORT, Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

    //update opportunity
    @PutMapping("/opportunities/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Opportunity> updateById(@PathVariable Long id, @RequestBody Opportunity opportunityDetails){
        Opportunity opportunity = opportunityRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Opportunity not exist"));

        Audit audit = new Audit();
        Date modifiedOn = new Date();
        audit.setOpportunityId(opportunity.getOpportunityId());
        audit.setAction("MODIFIED");
        audit.setRole(opportunity.getRole());
        audit.setModifiedBy(opportunity.getHiringManager());
        audit.setLocation(opportunity.getJoiningLocation());
        audit.setCreatedOn(opportunity.getPublishDate());
        audit.setModifiedOn(modifiedOn);

        opportunity.setHiringManager(opportunityDetails.getHiringManager());
        opportunity.setJoiningDate(opportunityDetails.getJoiningDate());
        opportunity.setJoiningLocation(opportunityDetails.getJoiningLocation());
        opportunity.setPublishDate(opportunityDetails.getPublishDate());
        opportunity.setRole(opportunityDetails.getRole());
        opportunity.setSkills(opportunityDetails.getSkills());
        opportunity.setDescription(opportunityDetails.getDescription());
        opportunity.setExperience(opportunityDetails.getExperience());

        auditRepository.save(audit);

        Opportunity updatedOpportunity = opportunityRepository.save(opportunity);
        return ResponseEntity.ok(updatedOpportunity);
    }
}
