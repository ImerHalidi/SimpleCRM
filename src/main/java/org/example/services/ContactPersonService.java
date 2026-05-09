package org.example.services;

import org.example.domain.ContactPerson;

import java.util.List;

public interface ContactPersonService {
    ContactPerson create(ContactPerson contactPerson);

    ContactPerson findById(Long id);

    List<ContactPerson>findAll();

    ContactPerson update(Long id,ContactPerson contactPerson);

    boolean delete(Long id);

}
