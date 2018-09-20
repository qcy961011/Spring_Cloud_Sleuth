package com.qiao.another_zipkin_server;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class AnotherZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnotherZipkinServerApplication.class, args);
    }

    private static final Logger log = Logger.getLogger(AnotherZipkinServerApplication.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate RestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String home(){
        log.log(Level.INFO,"hi is being called");
        return "hi i'm another";
    }

    public String info(){
        log.log(Level.INFO,"info is being called");
        return restTemplate.getForObject("http://localhost:8988",String.class);
    }

    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }


}
