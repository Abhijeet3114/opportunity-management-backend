package com.OpportunityManagement.backend;

import com.OpportunityManagement.backend.TestModel.AuditTest;
import com.OpportunityManagement.backend.TestModel.TestModelMethod;
import com.OpportunityManagement.backend.controller.OpportunityController;
import com.OpportunityManagement.backend.exception.ResourceNotFoundException;
import com.OpportunityManagement.backend.model.Audit;
import com.OpportunityManagement.backend.model.Opportunity;
import com.OpportunityManagement.backend.repository.AuditRepository;
import com.OpportunityManagement.backend.repository.OpportunityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {OpportunityController.class})
class BackendApplicationTests {

    //@Autowired
	TestModelMethod testModelMethod = new TestModelMethod();

    //@Autowired
    AuditTest auditTest = new AuditTest();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private OpportunityRepository opportunityRepository;

    @MockBean
    private AuditRepository auditRepository;


    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
    List<Opportunity> opportunityList = new ArrayList<>();
    Opportunity op1, op2;
    List<Audit> auditList = new ArrayList<>();

    @Before
    public void setup(){
        LocalDate now = LocalDate.now();
        short exp = 0;

        op1 = new Opportunity();
        op1.setOpportunityId(1L);
        op1.setHiringManager("Abhijeet Chavan");
        op1.setSkills("Java");
        op1.setExperience(exp);
        op1.setRole("Java Developer");
        op1.setDescription("Should Proficient with Software Development");
        op1.setJoiningLocation("Mumbai");
        op1.setPublishDate(new Date());
        op1.setJoiningDate(new Date());

        op2 = new Opportunity();
        op2.setOpportunityId(2L);
        op2.setHiringManager("Suresh Lakshete");
        op2.setSkills("Angular");
        op2.setExperience(exp);
        op2.setRole("Angular Developer");
        op2.setDescription("Should Proficient with Software Development");
        op2.setJoiningLocation("Hyderabad");
        op2.setPublishDate(new Date());
        op2.setJoiningDate(new Date());
        opportunityList.add(op1);
        opportunityList.add(op2);
    }

    @Test
    void testModel(){
	    testModelMethod.Role();
	    testModelMethod.description();
	    testModelMethod.endDate();
	    testModelMethod.experience();
	    testModelMethod.hiringManager();
	    testModelMethod.publishDate();
	    testModelMethod.skills();
	    testModelMethod.location();
	    testModelMethod.Id();

	    //audit

        auditTest.Action();
        auditTest.AuditId();
        auditTest.AuditOppoId();
        auditTest.createdOn();
        auditTest.Location();
        auditTest.modifiedOn();
        auditTest.Role();
    }

    //test controller getallopportunities
    @Test
    public void testGetAllOpportunities() throws Exception {
        Mockito.when(opportunityRepository.findAll()).thenReturn(opportunityList);
        mockMvc.perform(get("/api/v1/opportunities/all")).andExpect(status().isOk());
    }

    //test auditcontroller
    @Test
    public void testGetAudits() throws Exception {
        Mockito.when(auditRepository.findAll()).thenReturn(auditList);
        mockMvc.perform(get("/api/v1/opportunities/audit/all")).andExpect(status().isNotFound());
    }

    @Test
    public void testAddOpportunity() throws Exception {
        short exp = 0;
        op1 = new Opportunity();
        op1.setOpportunityId(1L);
        op1.setHiringManager("Abhijeet Chavan");
        op1.setSkills("Java");
        op1.setExperience(exp);
        op1.setRole("Java Developer");
        op1.setDescription("Should Proficient with Software Development");
        op1.setJoiningLocation("Mumbai");
        op1.setPublishDate(new Date());
        op1.setJoiningDate(new Date());
        String json = objectMapper.writeValueAsString(op1);
        Mockito.when(opportunityRepository.save(op1)).thenReturn(op1);
        mockMvc.perform(post("/api/v1/opportunities").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testupdateOpportunity() throws Exception {
        short exp = 0;
        op1 = new Opportunity();
        op1.setOpportunityId(1L);
        op1.setHiringManager("Abhijeet Chavan");
        op1.setSkills("Java");
        op1.setExperience(exp);
        op1.setRole("Java Developer");
        op1.setDescription("Should Proficient with Software Development");
        op1.setJoiningLocation("Mumbai");
        op1.setPublishDate(new Date());
        op1.setJoiningDate(new Date());
        String json = objectMapper.writeValueAsString(op1);
        Mockito.when(opportunityRepository.save(op1)).thenReturn(op1);
        mockMvc.perform(put("/api/v1/opportunities/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    public void testGetOpportunities() throws Exception {
        Mockito.when(opportunityRepository.getValidOpportunity()).thenReturn(opportunityList);
        mockMvc.perform(get("/api/v1/opportunities")).andExpect(status().isOk());
    }

    @Test
    public void testGetOpportunityById() throws Exception {
        try{
            Mockito.when(opportunityRepository.findById(1L).orElseThrow( () -> new ResourceNotFoundException("Opportunity does not exist"))).thenReturn(opportunityList.get(0));
            Mockito.when(opportunityRepository.findById(opportunityList.get(0).getOpportunityId()).orElseThrow( () -> new ResourceNotFoundException("Opportunity does not exist"))).thenReturn(opportunityList.get(0));
        }
        catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
        }
        mockMvc.perform(get("/api/v1/opportunities/{id}", 1L)).andExpect(status().isNotFound());
        mockMvc.perform(get("/api/v1/opportunities/{id}", 1L)).andReturn();
    }


    @Test
    public void testGetOpportunityByRole() throws Exception {
        try{
            Mockito.when(opportunityRepository.getOppotunityByRole("Java Developer")).thenReturn(opportunityList);
        }
        catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
        }
        mockMvc.perform(get("/api/v1/opportunities/search/{role}","Java Developer")).andExpect(status().isOk());
    }



}
