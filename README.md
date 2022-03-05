# Fullstack Loan Application System :receipt:
````diff
+ Loan Application Project is live now, you can access at : https://loan-application-ahmet.herokuapp.com

! Spring Security will be added asap.
````
## Summary
  A Loan Application System developed with Spring Boot and Thymeleaf. Receives __*loan application requests*__ and
returns the __*loan application result*__ according to the __*customer info*__ and __*credit score*__ of the customer.
Also sends a __*sms*__ to the customer about loan application result.

![new-customer.png](https://github.com/AkanAhmet/innova-patika-graduation-project-ahmetakan/blob/main/ProjectImages/new-customer.png)

## Details

* A system that customers can *signup*, *update* infos and *delete* **account**.

* When a **Customer** is added to the system, a **Credit Score** will be generated automatically according to the last
  digit of National ID.

* **Customer** can *apply for loan*.

* **Loan application result** will be calculated depending on Customer's **Credit Score** and **Salary**.

* **Loan application results** can only be *inquired* by National ID of the **Customer**

* **Loan application result** will be saved and sent to Customer's phone number.

## Table of Contents
- [Tech Stack](#tech-stack)
- [Prerequirements](#prerequirements)
- [How to Run](#how-to-run)
    - [Maven](#using-maven)
    - [Docker](#using-docker)
- [Usage](#usage)
    - [Customer Service](#customer-service)  
    - [Loan Application Service](#loan-application-service)
    - [Credit Score Service](#credit-score-service)
    - [SMS Service](#sms-service)
    - [Frontend](#frontend)
- [TODO](#todo)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Tech Stack

- [OpenJDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Spring Boot](https://spring.io/projects/spring-boot)  
- [Spring Data Jpa](https://spring.io/projects/spring-data-jpa)  
- [Spring Cloud](https://spring.io/projects/spring-cloud)  
- [Eureka Server](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)   
- [MySQL](https://www.mysql.com/)  
- [Mongo DB](https://www.mongodb.com/)  
- [Model Mapper](http://modelmapper.org/) 
- [Docker](https://www.docker.com/)  
- [Swagger OpenApi](https://swagger.io/specification/)
- [Lombok](https://projectlombok.org/)
- [Maven](https://maven.apache.org/)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Twilio](https://www.twilio.com/)

## Prerequirements

### To Run Project via Maven :
- [OpenJDK 11 ->](https://openjdk.java.net/projects/jdk/11/) jdk is compulsory, newest versions may cause incompatibilities.
- [Intellij Community ->](https://www.jetbrains.com/idea/) (optional) or any other capable IDE.
- [Maven ->](https://maven.apache.org/)  for create package .jar files.
 
### To Run Project via Docker :
- [Docker ->](https://www.docker.com/)  necessary for managing containers and images.
- [Docker Compose ->](https://docs.docker.com/compose/install) for compose docker-compose.yml, on Windows Systems its coming while installing Docker.

## How to Run

### Using Maven 

#### On Windows Systems =>

**1. Clone the repository**

```bash
git clone https://github.com/AkanAhmet/innova-patika-graduation-project-ahmetakan
```
**2. Go to the project directory**
```bash
cd innova-patika-graduation-project-ahmetakan
```
**3. Run:**
```bash
.\WindowsRunProjectViaMaven.bat
```

#### On Linux Systems =>

**1. Clone the repository**

```bash
git clone https://github.com/AkanAhmet/innova-patika-graduation-project-ahmetakan
```
**2. Go to the project directory**
```bash
cd innova-patika-graduation-project-ahmetakan
```
**3. Give permissions**
```bash
chmod +x LinuxRunProjectViaMaven.sh
```
**4. Run:**
```bash
./LinuxRunProjectViaMaven.sh
```

### Using Docker 

#### On Windows Systems =>

**1. Clone the repository**
```bash
git clone https://github.com/AkanAhmet/innova-patika-graduation-project-ahmetakan
```
**2. Go to the project directory**
```bash
cd innova-patika-graduation-project-ahmetakan
```
**3. Initialize jar files**
```bash
.\WindowsInitiatorForDocker.bat
```
**4. Run**  
````diff
- Run below command 2 times, at first it errors about Mysql Host(%), second time it runs successfully :-(  
````
```bash
docker-compose up
```

#### On Linux Systems =>

**1. Clone the repository**

```bash
git clone https://github.com/AkanAhmet/innova-patika-graduation-project-ahmetakan
```
**2. Go to the project directory**
```bash
cd innova-patika-graduation-project-ahmetakan
```
**3. Give the permissions**
```bash
chmod +x LinuxInitiatorForDocker.sh
```
**4. Initialize jar files**
```bash
./LinuxInitiatorForDocker.sh
```
**5. Run**   
````diff
- Run below command 2 times, at first it errors about Mysql Host(%), second time it runs successfully :-(  
````
```bash
sudo docker-compose up
```

## Usage

  The main requirement for the project was that the customer would be able to apply for a loan (optionally through UI) with their name,
surname, identification number, salary and phone. Then the eligibility for the customer would be calculated server-side, and the result would be
shown to the customer and then persisted to MongoDB for later queries. Along with that, an SMS notification would be sent to the customer. 

  Project consists of **four** microservices:  Customer Service, SMS Service, Loan Application Service, Credit Score Service. They are discovered by Eureka Service Registry and
the incoming requests are routed by Spring Cloud Gateway to the corresponding services. Used RestTemplate to communicate with other services. Used Log4j2 to logging system.
You can view Project images on ProjectImages folder. After running project you will able to see frontend page on: <a href="http://localhost:8767" target="_blank"> http://localhost:8767</a> . All services must be visible at <a href="http://localhost:8761" target="_blank"> http://localhost:8761.</a>


### Customer Service


This service saves customer related data to MySQLDb, it has a customer_service database, on which basic CRUD operations are possible. Unit tests are available for this service under Test package.

**Endpoints**

| Endpoint | Method |  Params | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|    `/api/customers` | GET | id  | Get customer info by the given ID number.                                             |
|    `/api/customers` | POST | none  |        Create customer. Request a body which contains `firstName`, `lastName`, `identificationNumber`, `salary` and the `phoneNumber` 
|    `/api/customers` | DELETE | id  |       Delete customer by the given ID number. 
|    `/api/customers` | PUT | id  |       Update customer infos . Request a body which contains `firstName`, `lastName`, `identificationNumber`, `salary` and the `phoneNumber` 
|   `http://localhost:8763/swagger-ui/index.html` | GET | none | Swagger documentation page.


### Credit Score Service

  This is a service which <b>fictionally</b> holds the customers credit scores. 
In our situation, it just calculates the credit score according to a simple algorithm and returns it if queried. The credit score is calculated according to the last number of the identification number:

- 0 -> 1000
- 2 -> 200
- 4 -> 400
- 6 -> 600
- 8 -> 800

**Endpoints**

| Endpoint | Method |  Params | Description                                                                                                                                                   |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------      |
| `/api/credit-score` | GET | id | Get the credit score by the given id number;
| `/api/credit-score` | POST | id | Credit score calculating and initializing shortly after customer created automatically. 
|`http://localhost:8764/swagger-ui/index.html` | GET | none | Swagger documentation page.|


### Loan Application Service

  The main job of the service is to process the loan application request, send eligibility notices and log the applications 
to the MongoDB for later queries. It simply takes customer infos and returns a result message which contains loan application status and loan limit. How it decides eligibility and credit limit? 
  Well, if the credit score of the customer which is acquired from **Credit Score Service** is lower than 500, the customer is not eligible. If it is higher, and the monthly income of the customer is less then 5000, then the customer is eligible for 10000, else if the monthly income is higher than 5000 the limit raises to 20000 . If the score is higher then 1000, the loan limit is calculated as follows: limit = income*4. 
  
  Loan application Service sends the result to the SMS Service to notify customer on phone. Some unit tests are available under Test package for this service.

**Endpoints**

| Endpoint | Method |  Params | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|    `/api/loan-applications` | GET | id  | Get the customer loan application list by the given ID number.                                                     |
|    `/api/loan-applications` | POST | none  |  Post a new loan application. Request a body which contains customer infos, communicates with credit score service and takes credit score. Afterall evaluates customer loan application status and loan limit.
|   `http://localhost:8765/swagger-ui/index.html` | GET | none | Swagger documentation page.


### SMS Service

  A simple service which takes loan application status and customer infos from Loan Application Service and sends sms to customer via **Twilio Api**.

**Endpoints**

| Endpoint | Method |  Params | Description                                                                                                                                                   |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------      |
| `/api/sms-service` | POST | none | Request a body which comes from loan application service, and return sms status info as boolean|
| `http://localhost:8766/swagger-ui/index.html` | GET | none | Swagger documentation page.|


### Frontend

  It is made with Spring and Thymeleaf couple. Has a very simple design which consists of ten pages, four of them for handling errors. 
Can be accessed on ``http://localhost:8767``. 


## TODO
- [Feign Client](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html)
- [Kubernetes](https://kubernetes.io/)
- [Zipkin](https://spring.io/projects/spring-cloud-sleuth)
- [Spring Cloud Hystrix](https://cloud.spring.io/spring-cloud-netflix/multi/multi__circuit_breaker_hystrix_clients.html)
- [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/reference/html/)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
Distributed under the MIT License. See [LICENSE](https://github.com/AkanAhmet/innova-patika-graduation-project-ahmetakan/blob/main/LICENSE) for more information.  

## Contact
You may want get contact to me for any errors or questions at ahmet26es1@gmail.com.
