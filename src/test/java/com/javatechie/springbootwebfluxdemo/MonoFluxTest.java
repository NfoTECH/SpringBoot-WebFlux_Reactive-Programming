package com.javatechie.springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        // Create a Mono instance that emits a single value "java techie" (publisher)
        Mono<?> mono = Mono.just("java techie")
                .then(Mono.error(new RuntimeException("Exception Occurred")))
                .log(); // log() is used to print the value
        // Subscribe to the Mono and print the value
        mono.subscribe(System.out::println, (e) -> System.err.println("Exception is " + e));
    }

    @Test
    public void testFlux () {
        // Create a Flux instance that emits multiple values (publisher)
        Flux<String> fluxString = Flux.just("java", "python", "node", "ruby", "go", "kotlin")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .concatWithValues("Azure")
                .log();
        fluxString.subscribe(System.out::println, (e) -> System.err.println("Exception is " + e));
    }
}
