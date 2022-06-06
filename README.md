# Payment Gateway

Created a basic Payment gateway.

## Introduction
This project is created with Java Spring boot and is based on the general working principle of a payment gateway. 
An API Endpoint is exposed to accept the payment details. 

#### Endpoint : ​/onboardingManagement​/api​/v1​/makePayment​/pay

#### Details taken into consideration are:
- `amount` (The total amount to be processed).
- `cardNumber` (The Credit Card number).
- `cvv` (cvv for the Credit Card).
- `merchantId` (The Merchant's id to whom the payment is supposed to be made).

#### Database Details:




#### Name: `payment_db`

#### Table Names:

- `MERCHANT_BANK`(MERCHANT_ID, TRANSACTION_ID, BALANCE)
- `CUSTOMER_BANK`(CUSTOMER_ID, CARD_NUMBER, CVV, BALANCE)


### Note:
- The card details need to be stored based on PCI DSS compliance standards but for this demo, the card details are stored in the DB in string format.
- The Card expiration date is not taken into consideration.
- Currency is not taken into Consideration.
#### Improvements that can be done:
- Adding UI.
- Using PCI DSS compliance standards for storing card details.
- Using complete card details like Name on the Card and Expiry Date.
- Instead of using the merchant Id we can use merchant name and create a mapping in the Merchant table where Name corresponds to the ID.
  


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

#### Database
- `Prerequisite : Docker required.`
- Run
```bash
 docker run --name payments-db -e MYSQL_ROOT_PASSWORD=admin -p 3306:3306 -d mysql:8.0 
```
- Populate the db: 
- Username: root 
- Password: admin
```bash
docker exec -it payments-db /bin/bash 
mysql -u root -p 
 ```
- #### Run the queries in db-dump.sql at location src/main/resources/utilities/db-dump.sql
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


### Data to use:

- The merchant id is fixed : `1191f989-0390-45ab-bd54-1cf312ad1b4e`

| Card Number         | cvv  | Customer Id                            | Balance   
| ------------------- | ---- | -------------------------------------- | ----------
| `6227172066964207`  | 324  | `f1c9a865-2b64-46fb-9286-c1011aa50b29` | 10000
| `5019216085126571`  | 244  | `2bcd59ea-f4b3-47a0-b714-d82da5f1a904` | 50000
| `6361695649657969`  | 789  | `9181536c-69fd-4e71-abc2-45dc89360fa0` | 2500
