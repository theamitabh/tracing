package com.acheekoth.tracing.service;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeliveryService {

    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/arrangeDelivery")
    public String arrangeDelivery(@RequestHeader HttpHeaders headers) {
        Span span = tracer.buildSpan("arrangeDelivery").start();
        String result = "";
        try {
            Thread.sleep((long) (Math.random() * 1000));
            HttpEntity entity = new HttpEntity<>("");
            result += restTemplate.exchange("http://logistics:8080/transport", HttpMethod.GET, entity, String.class).getBody();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            span.finish();
        }
        return result + "<BR>Your order is delivered!";
    }
}
