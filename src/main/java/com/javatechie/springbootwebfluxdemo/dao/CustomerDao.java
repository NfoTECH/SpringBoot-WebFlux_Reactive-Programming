package com.javatechie.springbootwebfluxdemo.dao;

import com.javatechie.springbootwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.time.Duration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int seconds) {

        // sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // getCustomer() returns a list of 10 customers
    public List<Customer> getCustomer() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("Customer " + i + " is being processed by " + Thread.currentThread().getName()))
                .mapToObj(i -> new Customer(i, "name" + i)).
                collect(Collectors.toList());
    }

    // getCustomerStream() returns a Flux of 10 customers
    public Flux<Customer> getCustomerStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing customer " + i + " on thread " + Thread.currentThread().getName()))
                .map(i -> new Customer(i, "name" + i));
    }

    public Flux<Customer> getCustomerList(){
        return Flux.range(1, 15)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing customer " + i + " on thread " + Thread.currentThread().getName()))
                .map(i -> new Customer(i, "name" + i));
    }
}
