package com.javatechie.springbootwebfluxdemo.handler;

import com.javatechie.springbootwebfluxdemo.dao.CustomerDao;
import com.javatechie.springbootwebfluxdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {
    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomers (ServerRequest serverRequest) {
        Flux<Customer> customerStream = customerDao.getCustomerStream();
        return ServerResponse.ok()
                .contentType(org.springframework.http.MediaType.TEXT_EVENT_STREAM)
                .body(customerStream, Customer.class);
    }
}
