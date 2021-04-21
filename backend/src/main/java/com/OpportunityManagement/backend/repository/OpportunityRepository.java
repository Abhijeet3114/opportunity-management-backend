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

    @Query("select t from Opportunity t where t.Role Like %:searchString% or t.skills Like %:searchString% or t.HiringManager Like %:searchString% or t.JoiningLocation Like %:searchString% order by publishDate desc")
    public List<Opportunity> getOppotunityByRole(@Param("searchString") String role);
    //@Query("select t from Opportunity t where t.Role Like %:searchString% or t.Skills Like %:searchString% or t.HiringManager Like %:searchString% or t.JoiningLocation;")

    @Query("select t from Opportunity t where t.joiningDate >= CURRENT_TIMESTAMP order by publishDate desc")
    public List<Opportunity> getValidOpportunity();

    @Query("select o.Role, count(*) as value from Opportunity o where o.JoiningLocation Like '%mu%mb%ai' group by o.Role")
    public List<Object> getViewMumbai();

    @Query("select o.Role, count(*) from Opportunity o where o.JoiningLocation Like '%Ban%g%a%lo%re' group by o.Role")
    public List<Object> getViewBanglore();

    @Query("select o.Role, count(*) as value from Opportunity o where o.JoiningLocation Like '%Del%hi%' group by o.Role")
    public List<Object> getViewDelhi();

    @Query("select o.Role, count(*) as value from Opportunity o where o.JoiningLocation Like '%Hy%de%ra%bad%' group by o.Role")
    public List<Object> getViewHyderabad();

}