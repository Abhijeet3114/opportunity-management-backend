package com.OpportunityManagement.backend.controller;

import com.OpportunityManagement.backend.exception.ResourceNotFoundException;
import com.OpportunityManagement.backend.model.Opportunity;
import com.OpportunityManagement.backend.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:8080")

public class OpportunityController {
    @Autowired
    private OpportunityRepository opportunityRepository;

    //get all Opportunities
    @GetMapping("/opportunities")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Opportunity> getOpportunities(){
        return opportunityRepository.getValidOpportunity();
    }

    //add opportunity
    @PostMapping("/opportunities") //post method to save opportunity details
    @CrossOrigin(origins = "http://localhost:4200")
    public Opportunity addOpportunity( @RequestBody Opportunity opportunity){ //@reuestbody used because post method needs json format
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
    public ResponseEntity<List<Opportunity>> getOpportunityByRole(@PathVariable String role){
        List<Opportunity> opportunity = opportunityRepository.getOppotunityByRole(role);
        return ResponseEntity.ok(opportunity);
    }

    //trends
    @GetMapping("/opportunities/trends/Mumbai")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Object>> getViewMumbai(){
        List<Object> opportunity = opportunityRepository.getViewMumbai();
        return ResponseEntity.ok(opportunity);
    }

    @GetMapping("/opportunities/trends/Banglore")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Object>> getViewBanglore(){
        List<Object> opportunity = opportunityRepository.getViewBanglore();
        return ResponseEntity.ok(opportunity);
    }

    @GetMapping("/opportunities/trends/Delhi")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Object>> getViewDelhi(){
        List<Object> opportunity = opportunityRepository.getViewDelhi();
        return ResponseEntity.ok(opportunity);
    }

    @GetMapping("/opportunities/trends/Hyderabad")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Object>> getViewHyderabad(){
        List<Object> opportunity = opportunityRepository.getViewHyderabad();
        /*List<Map<String, String>> result = new ArrayList<>();
        for (int i = 0; i < opportunity.size(); i++){
            Map<String, String> temp = new HashMap<>();
            temp.put("name")
        }*/
        return ResponseEntity.ok(opportunity);
    }



    //update opportunity
    @DeleteMapping("/opportunities/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable Long id){
        Opportunity opportunity = opportunityRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Opportunity not exist"));

        String opportunity_INSHORT = opportunity.getRole()+" opportunity created by "+opportunity.getHiringManager()+" on "+
                opportunity.getPublishDate()+" is successfully deleted";

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

        opportunity.setHiringManager(opportunityDetails.getHiringManager());
        opportunity.setJoiningDate(opportunityDetails.getJoiningDate());
        opportunity.setJoiningLocation(opportunityDetails.getJoiningLocation());
        opportunity.setPublishDate(opportunityDetails.getPublishDate());
        opportunity.setRole(opportunityDetails.getRole());
        opportunity.setSkills(opportunityDetails.getSkills());
        opportunity.setDescription(opportunityDetails.getDescription());
        opportunity.setExperience(opportunityDetails.getExperience());

        Opportunity updatedOpportunity = opportunityRepository.save(opportunity);
        return ResponseEntity.ok(updatedOpportunity);
    }
}
