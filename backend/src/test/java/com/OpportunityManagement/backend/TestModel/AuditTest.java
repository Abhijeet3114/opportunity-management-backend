package com.OpportunityManagement.backend.TestModel;

import com.OpportunityManagement.backend.model.Audit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


public class AuditTest {
    //@Autowired
    Audit audit = new Audit();

    @Test
    public void Role(){
        audit.setRole("Software Engineer");
        String role = audit.getRole();
        Assertions.assertEquals("Software Engineer", role);
    }

    @Test
    public void Action(){
        audit.setAction("CREATED");
        String action = audit.getAction();
        Assertions.assertEquals("CREATED", action);
    }

    @Test
    public void Location(){
        audit.setLocation("Delhi");
        Assertions.assertEquals(audit.getLocation(), "Delhi");
    }

    @Test
    public void AuditId(){
        audit.setAuditId(1L);
        Long id = audit.getAuditId();
        Assertions.assertEquals(1L, id);
    }

    @Test
    public void AuditOppoId(){
        audit.setOpportunityId(1L);
        Long id = audit.getOpportunityId();
        Assertions.assertEquals(1L, id);
    }

    @Test
    public void createdOn(){
        Date date = new Date();
        audit.setCreatedOn(date);
        Assertions.assertEquals(audit.getCreatedOn(), date);
    }

    @Test
    public void modifiedOn(){
        Date date = new Date();
        audit.setModifiedOn(date);
        Assertions.assertEquals(audit.getModifiedOn(), date);
    }


}
