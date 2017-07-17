package io.jenksi.john;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by johnjenkins on 7/17/17.
 */
@RestController
@RequestMapping(value = "/api/jenksi")
public class ClientController {

    private EurekaClient eurekaClient;//instance to get the services from the Eureka server
    private RestTemplateBuilder restTemplateBuilder;

    public ClientController(EurekaClient eurekaClient, RestTemplateBuilder restTemplateBuilder) {
        this.eurekaClient = eurekaClient;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public String callService(){
        //create a new restemplate using the builder
        RestTemplate restTemplate = restTemplateBuilder.build();
        //args are name of service and boolean as to wheter is is secure or not Returns and IntanceInfo
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("service", false);

        //get the base url for the
        String baseURL = instanceInfo.getHomePageUrl();
        //get the info from the service using the restTemplate and assign to a ResponseEntity
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseURL, HttpMethod.GET, null,String.class);
        return responseEntity.getBody();

    }
}
