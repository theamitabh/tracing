package com.acheekoth.tracing;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EShopController {

    @RequestMapping(value = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        return "Checkout successfull";
    }

}
