package org.example.services;

import org.example.domain.Opportunity;

import java.util.List;
import java.util.Map;

public interface OpportunityService {
    Opportunity create(Opportunity opportunity);
    Opportunity findById(Long id);
    List<Opportunity> findAll();
    Opportunity update(Long id, Opportunity opportunity);
    boolean delete(Long id);
    List<Opportunity>findByCostumerId(Long customerId);
    List<Opportunity> filter(String status,Long customerId);
    Opportunity changeStatus(Long id, String status);
    Map<String ,Double> getSummaryByStatus();
    Map<String,Integer> getcountByStatus();

}
