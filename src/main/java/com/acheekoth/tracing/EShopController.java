package com.acheekoth.tracing;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acheekoth.tracing.service.BillingService;
import com.acheekoth.tracing.service.DeliveryService;
import com.acheekoth.tracing.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;


import io.opentracing.Tracer;
import io.opentracing.Span;


@RestController
public class EShopController {
    @Autowired
    private Tracer tracer;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private DeliveryService deliveryService;

    @RequestMapping(value = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        Span span = tracer.buildSpan("checkout").start();
        String result= "";
        try {
            tracer.scopeManager().activate(span);
            result = result + inventoryService.createOrder() + System.lineSeparator();
            result = result + billingService.payment()+ System.lineSeparator();
            result += deliveryService.arrangeDelivery();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            span.finish();
            return result;
        }
    }
}
