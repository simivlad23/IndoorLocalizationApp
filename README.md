# IndoorLocalizationApp

> A microservice architecture created with JHipster. Uses Spring Cloud, Spring Boot, Angular, and MySQL for a simple indoor localiza applications. 

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/simivlad23/IndoorLocalizationApp.git
```

First time you have to start Eureka Discovery Server and after that you can start the others microservices

1. Start the registry by running `./mvnw ` in the `jhipster-registry` directory.
2. Start the gateway-app by running `./mvnw ` in the `gateway-app` directory.
3. Start the indoortracker by running `./mvnw ` in the `indoortracker` directory.

    ```
    yarn
    ./mvnw 
    ``` 
    
 This application is configured for Service Discovery and Configuration with the JHipster-Registry. On launch, it will refuse to start if it is not able to connect to the JHipster-Registry at http://localhost:8761. 
 
 You should be able to see the `gateway` app at <http://localhost:8080> and the indoor features (from the `indoortracker` app)
