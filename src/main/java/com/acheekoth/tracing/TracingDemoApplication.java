package com.acheekoth.tracing;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class TracingDemoApplication {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	public  JaegerTracer getTracer() {
		return Configuration.fromEnv("EShop").getTracer();
	}

	public static void main(String[] args) {
		SpringApplication.run(TracingDemoApplication.class, args);
	}
}
