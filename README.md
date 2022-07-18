# springboot eureka service registry and discovery
* If we don't have the spring registry and discovery we have to go to each service to check the health of each services.
* To activate  eruker related configuration we have to annotate main class with `@EnableEurekaServer`.
* The default port of eureka server is 8761.
* One we add the configuration properties in application.yaml we can access eurekaserver in:
    > http://localhost:8761/
  
* When microservices get register in eureka server it will be display on `Instances currently registered with Eureka` in `http://localhost:8761/`

* In `doctor-service` and `patient-service` we can make it discovery client by annotate in main class with `@EnableEurekaClient`
* Once adding all the configuration in `doctor service` and `patient service` application.yaml, run the services, then we can see the both services is registered in eureka server by checking on
  > http://localhost:8761/
  
* From  eureka dashboard we can see the staus of the service client. We can click on the link to check the info of the apolication using actuator. In my case url for patient service is:
  >  host.docker.internal:patient-service:8081
   
  which will open the url:
  > http://host.docker.internal:8081/actuator/info
  
  If we want to check the location end point from MainController of Patient Service  we can check it on:
  > http://host.docker.internal:8081/location
  
* In `doctor portal` we have to annotate main class with `@EnableDiscoveryClient`
* In `doctor portal` we set `register-with-eureka` to false in application.yaml because we don't want to register doctor portal in eureka but want to fetch data from eureka server. By default `register-with-eureka` and `fetch-registry` is true.
* Once we do all the configuration in `doctor portal`, we can run the doctor portal and call doctor service using doctor portal using:
  > http://localhost:7081/doctors