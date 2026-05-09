package org.example.services;
import org.example.domain.Customer;

import java.util.List;
public interface CustomerService {
    Customer create(Customer customer);
    Customer findById(Long id);
    List<Customer>findAll();
    Customer update(Long id,Customer customer);
    boolean delete(Long id);
}
