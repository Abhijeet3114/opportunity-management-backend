package com.OpportunityManagement.backend.TestModel;

import com.OpportunityManagement.backend.model.Opportunity;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestModelMethod {

    //test getter and setters
    Opportunity opportunity = new Opportunity();

    @Test
    public void Role(){
        opportunity.setRole("Software Engineer");
        String role = opportunity.getRole();
        Assertions.assertEquals("Software Engineer", role);
    }

    @Test
    public void Id(){
        opportunity.setOpportunityId((long)101);
        Long id = opportunity.getOpportunityId();
        Assertions.assertEquals(101, id);
    }

    @Test
    public void skills(){
        opportunity.setSkills("Java, Angular");
        String skills = opportunity.getSkills();
        Assertions.assertEquals("Java, Angular", skills);
    }

    @Test
    public void experience(){
        opportunity.setExperience((short)5);
        Short experience = opportunity.getExperience();
        Assertions.assertEquals((short)5, experience);
    }

    @Test
    public void publishDate(){
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        String date_str = ft.format(date).toString();
        opportunity.setPublishDate(date);
        String date_response = ft.format(opportunity.getPublishDate()).toString();
        Assertions.assertEquals(date_str, date_response);
    }

    @Test
    public void endDate(){
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        String date_str = ft.format(date).toString();
        opportunity.setJoiningDate(date);
        String date_response = ft.format(opportunity.getJoiningDate()).toString();
        Assertions.assertEquals(date_str, date_response);
    }

    @Test
    public void hiringManager(){
        opportunity.setHiringManager("Abhijeet Chavan");
        String name = opportunity.getHiringManager();
        Assertions.assertEquals("Abhijeet Chavan", name);
    }

    @Test
    public void description(){
        opportunity.setDescription("Proficient with Java, Angular");
        String skills = opportunity.getDescription();
        Assertions.assertEquals("Proficient with Java, Angular", skills);
    }

    @Test
    public void location(){
        opportunity.setJoiningLocation("Mumbai");
        String location = opportunity.getJoiningLocation();
        Assertions.assertEquals("Mumbai", location);
    }
}
