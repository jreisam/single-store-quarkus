package org.acme.service;

import org.acme.dto.CustomerDto;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDto> findAllCustomers() {
        List<CustomerDto> customers = new ArrayList<>();
        customerRepository.findAll().stream().forEach(
                item -> {
                    customers.add(mapper(item));
                }
        );
        return customers;
    }

    public void createCustomer(CustomerDto dto){
        customerRepository.persist(mapper(dto));
    }

    public void updateCustomer(Long id, CustomerDto dto) {
        CustomerEntity newCustomer = customerRepository.findById(id);
        newCustomer.setName(dto.getName());
        newCustomer.setAddress(dto.getAddress());
        newCustomer.setPhone(dto.getPhone());
        newCustomer.setEmail(dto.getEmail());
        newCustomer.setAge(dto.getAge());

        customerRepository.persist(newCustomer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    private CustomerDto mapper(CustomerEntity entity) {
        CustomerDto dto = new CustomerDto();
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        return dto;
    }
    private CustomerEntity mapper(CustomerDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
