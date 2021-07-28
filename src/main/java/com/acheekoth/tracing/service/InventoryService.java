package com.acheekoth.tracing.service;


import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    Tracer tracer;

    public String createOrder(Span parent) {
        Span span = tracer.buildSpan("createOrder").asChildOf(parent).start();
        // Add a random delay to the service
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            span.finish();
        }
        return "Your order has been created!";
    }
}
