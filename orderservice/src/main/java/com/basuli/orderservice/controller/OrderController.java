package com.basuli.orderservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.basuli.orderservice.dto.RequestOrder;
import com.basuli.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
@RequestMapping("order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody RequestOrder requestOrder){
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(requestOrder));
    }

    //exception handler for fallabck method
    //  @ExceptionHandler(RuntimeException.class)
    // @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    // public String handleRuntimeException(RuntimeException ex) {
    //     return ex.getMessage();
    // }

    // Implementing fallback method for circuit breaker
    public CompletableFuture<String> fallbackMethod(RequestOrder requestOrder, RuntimeException runtimeException) {
        //throw new RuntimeException("Inventory service is currently unavailable. Please try again later.", throwable);
        return CompletableFuture.supplyAsync(()->"inventory Service is currently unavailable. Please try again later.");
    }
}
