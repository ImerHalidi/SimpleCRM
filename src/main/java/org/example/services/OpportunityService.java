package org.example.services;

import org.example.domain.Opportunity;

import java.util.List;

public interface OpportunityService {
    Opportunity create(Opportunity opportunity);
    Opportunity findById(Long id);
    List<Opportunity> findAll();
    Opportunity update(Long id, Opportunity opportunity);
    boolean delete(Long id);
    List<Opportunity>findByCostumerId(Long customerId);

}
