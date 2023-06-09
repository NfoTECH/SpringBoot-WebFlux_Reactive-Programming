package com.javatechie.springbootwebfluxdemo.service;

import com.javatechie.springbootwebfluxdemo.dao.CustomerDao;
import com.javatechie.springbootwebfluxdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customerList =  customerDao.getCustomer();
        long end = System.currentTimeMillis();
        System.out.println("Total time taken : " + (end - start));
        return customerList;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customerList =  customerDao.getCustomerStream();
        long end = System.currentTimeMillis();
        System.out.println("Total time taken : " + (end - start));
        return customerList;
    }
}
