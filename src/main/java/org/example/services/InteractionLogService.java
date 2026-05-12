package org.example.services;

import org.example.domain.InteractionLog;

import java.util.List;

public interface InteractionLogService {
    InteractionLog create(InteractionLog interactionLog);
    InteractionLog findById(Long id);
    List<InteractionLog>findAll();
    InteractionLog update(Long id,InteractionLog interactionLog);
    boolean delete(Long id);

}
