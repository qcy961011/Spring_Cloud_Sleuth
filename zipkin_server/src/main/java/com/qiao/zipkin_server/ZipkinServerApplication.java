package com.qiao.zipkin_server;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }

    private  static final Logger log = Logger.getLogger(ZipkinServerApplication.class.getName());

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/hi")
    public String callHome(){
        log.log(Level.INFO,"calling trace zipkinServer");
        return restTemplate.getForObject("http://localhost:8989",String.class);
    }

    @RequestMapping("/info")
    public String info(){
        log.log(Level.INFO,"calling trace zipkinServer");
        return "i'm zipkin_server";
    }

    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
