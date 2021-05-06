package com.OpportunityManagement.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Opportunity")
@Table(name = "Opportunity")
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opportunityId")
    private Long opportunityId;

    @Column(name = "Role")
    private String Role;

    @Column(name = "Skills")
    private String skills;

    @Column(name = "publishDate")
    private Date publishDate;

    @Column(name = "joiningDate")
    private Date joiningDate;

    @Column(name = "Location")
    private String JoiningLocation;

    @Column(name = "HiringManager")
    private String HiringManager;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Experience")
    private short Experience;


    public Opportunity() {

    }

    /*public Opportunity(String opportunityRole) {
        this.opportunityRole = opportunityRole;
    }*/

    public Long getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public short getExperience() {
        return Experience;
    }

    public void setExperience(short experience) {
        Experience = experience;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }


    public String getHiringManager() {
        return HiringManager;
    }

    public void setHiringManager(String hiringManager) {
        HiringManager = hiringManager;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getJoiningLocation() {
        return JoiningLocation;
    }

    public void setJoiningLocation(String JoiningLocation) {
        this.JoiningLocation = JoiningLocation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
