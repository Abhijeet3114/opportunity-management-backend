package com.OpportunityManagement.backend.repository;

import com.OpportunityManagement.backend.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    //@Query("select t from Opportunity t where t.Role Like %:searchString% or t.skills Like %:searchString% or t.HiringManager Like %:searchString% or t.JoiningLocation Like %:searchString% order by publishDate desc")
    @Query("select t from Opportunity t where :searchString like concat('%',t.HiringManager,'%') or :searchString like concat('%',t.JoiningLocation,'%') or :searchString like concat('%',t.Role,'%') or :searchString like concat('%',t.skills,'%') order by publishDate desc")
    public List<Opportunity> getOppotunityByRole(@Param("searchString") String role);
    //@Query("select t from Opportunity t where t.Role Like %:searchString% or t.Skills Like %:searchString% or t.HiringManager Like %:searchString% or t.JoiningLocation;")
    //select t from Opportunity where "%searchString%" like concat("%",t.HiringManager,"%") or "%searchString%" like concat("%",t.JoiningLocation,"%") or "%searchString%" like concat("%",t.Role,"%") or "%searchString%" like concat("%",t.skills,"%") order by publishDate desc;
    @Query("select t from Opportunity t where t.joiningDate >= CURRENT_TIMESTAMP order by publishDate desc")
    public List<Opportunity> getValidOpportunity();

}