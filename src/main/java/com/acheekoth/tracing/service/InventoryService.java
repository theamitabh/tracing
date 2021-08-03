package com.acheekoth.tracing.service;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class InventoryService {

    @Autowired
    Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/createOrder")
    public String createOrder(@RequestHeader HttpHeaders headers) {
        Span span = tracer.buildSpan("createOrder").start();
        // Add a random delay to the service
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            span.finish();
        }
        return "Your order has been created!";
    }
}
