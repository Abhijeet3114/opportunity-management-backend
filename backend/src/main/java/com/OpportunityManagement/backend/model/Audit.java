package com.OpportunityManagement.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name="Audit")
@Table(name="Audit")
public class Audit {

    /*
    * Table Structure
    *
    * ID   oppo_id  Action      EditedBy           Role        Location    CreatedOn   ModifiedOn
    * 1     10      Created     abc@gmail.com   Soft Dev    Mumbai      05-05-2021      05-05-2021
     *2     10      Created     xyz@gmail.com   Soft Dev    Mumbai      05-05-2021      06-05-2021
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditId")
    Long auditId;

    @Column(name="opportunityId")
    Long opportunityId;

    @Column(name = "action")
    String action;

    @Column(name="modifiedBy")
    String modifiedBy;

    @Column(name="role")
    String role;

    @Column(name = "location")
    String location;

    @Column(name = "createdOn")
    Date createdOn;

    @Column(name="modifiedOn")
    Date modifiedOn;

    public Audit() {
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
