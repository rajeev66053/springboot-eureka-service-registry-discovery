package com.java.doctorportal;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //@Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    @GetMapping("/doctors")
    public String getDoctors(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("DOCTOR-SERVICE", false);
        String baseUrl = nextServerFromEureka.getHomePageUrl();//http://localhost:port
        return restTemplate.getForObject(baseUrl+"/location", String.class);

    }

    @GetMapping("/patients")
    public String getPatients(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("PATIENT-SERVICE", false);
        String baseUrl = nextServerFromEureka.getHomePageUrl();//http://localhost:port
        return restTemplate.getForObject(baseUrl+"/location", String.class);

    }
}