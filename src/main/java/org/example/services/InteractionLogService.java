package org.example.services;

import org.example.domain.InteractionLog;

import java.util.List;
import java.util.Map;

public interface InteractionLogService {
    InteractionLog create(InteractionLog interactionLog);
    InteractionLog findById(Long id);
    List<InteractionLog>findAll();
    InteractionLog update(Long id,InteractionLog interactionLog);
    List<InteractionLog>findInteractionLogsByCustomerID(Long id);
    boolean delete(Long id);

    List<Map<String, Object>> findDetailedInteractionsByCustomerId(Long customerId);

}
