# Payment Gateway

Created a basic Payment gateway.



## Installation

### Setting up Java JDK

- Java version required 15.x
- Open a new terminal and run 
```bash
java -version
```
If you don't have a JDK installed, you can download it from [here](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html).


### Setting up Maven
- Download Maven [here](https://maven.apache.org/download.cgi).
- Check if installed properly.
```bash
mvn -version
```


### Setting up Project

- Clone the repo.
- From the root folder run the below command to download all the required dependencies.
```bash
mvn install -DskipTests  
```

- Run the project by specifying the properties file required to run the project.
```bash
java -jar target/payment-gateway-0.0.1.jar --spring.config.location=src/main/resources/application.properties 
```

### Accessing the Swagger UI
- Click [here](http://localhost:8082/docs) to access the Swagger UI.
