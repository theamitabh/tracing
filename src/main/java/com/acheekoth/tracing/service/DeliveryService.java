package com.acheekoth.tracing.service;


import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private Tracer tracer;

    @Autowired
    private LogisticsService logisticsService;

    public String arrangeDelivery() {
        Span spanParent = tracer.scopeManager().activeSpan();
        Span curSpan = tracer.buildSpan("arrangeDelivery").start();
	String result = "";
        try {
            tracer.scopeManager().activate(curSpan);
            // Add a random delay to the service
            Thread.sleep((long) (Math.random() * 1000));
	        result = logisticsService.transport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            curSpan.finish();
            // setting parent as activespan hereon
            tracer.scopeManager().activate(spanParent);
        }
        return result + "\r\nYour order is delivered!";
    }
}
