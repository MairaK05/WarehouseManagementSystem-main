package com.warehouse_backend.wh_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.warehouse_backend.wh_backend.entities.CustomerEntity;
import com.warehouse_backend.wh_backend.repositories.CustomerRepository;
import com.warehouse_backend.wh_backend.web.errors.NotFoundException;
import com.warehouse_backend.wh_backend.web.models.Customer;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository; // repository for customers and their attributes

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerEntity translateWebToDb(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerID(customer.getCustomerID());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());
        entity.setPhoneNumber(customer.getPhoneNumber());
        customer.setCreatedAt(customer.getCreatedAt());
        return entity;
    }

    public List<Customer> getAllCustomer(String filterEmail) {
        List<Customer> customers = new ArrayList<>();
        if (StringUtils.hasLength(filterEmail)) {
            CustomerEntity entity = this.customerRepository.findByEmail(filterEmail);
            customers.add(this.translateDbToWeb(entity));
        } else {
            Iterable<CustomerEntity> entities = this.customerRepository.findAll();
            entities.forEach(entity -> {
                customers.add(this.translateDbToWeb(entity));
            });
        }
        return customers;
    }

    public Customer getCustomer(long id) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("NO CUSTOMER WITH THIS ID");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Customer createOrUpdate(Customer customer) {
        CustomerEntity entity = this.translateWebToDb(customer);
        entity = this.customerRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteCustomer(long id) {
        this.customerRepository.deleteById(id);
    }

    private Customer translateDbToWeb(CustomerEntity entity) { // Translate from Database Entry "CustomerEntity" to
        return new Customer(entity.getCustomerID(), entity.getFirstName(), entity.getLastName(), entity.getEmail(),
                entity.getPhoneNumber(), entity.getCreatedAt());
    }
}
