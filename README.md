Microservices with Spring Boot and Spring Cloud
------------------------------------------------
Step 01 - Setting up Limits Microservice - V2  

Step 02 - Creating a hard coded limits service - V2

Step 03 - Enhance limits service - Get configuration from application props - V2

Step 04 - Setting up Spring Cloud Config Server - V2

Step 05 - Installing Git and Creating Local Git Repository - V2

Step 06 - Connect Spring Cloud Config Server to Local Git Repository - V2

Step 07 - Connect Limits Service to Spring Cloud Config Server - V2

Step 08 - Configuring Profiles for Limits Service - V2

Step 09 - Introduction to Currency Conversion & Exchange Microservices - V2

Step 10 - Setting up Currency Exchange Microservice - V2

Step 11 - Create a simple hard coded currency exchange service - V2

Step 12 - Setting up Dynamic Port in the the Response - V2

Step 13 - Configure JPA and Initialized Data - V2

Step 14 - Create a JPA Repository - V2

Step 15 - Setting up Currency Conversion Microservice - V2

Step 16 - Creating a service for currency conversion - V2

Step 17 - Invoking Currency Exchange from Currency Conversion Microservice - V2

Step 18 - Using Feign REST Client for Service Invocation - V2

Step 19 - Understand Naming Server and Setting up Eureka Naming Server - V2

Step 20 - Connect Currency Conversion & Currency Exchange Microservices - V2

Step 21 - QuickStart by Importing Microservices

Step 22 - Load Balancing with Eureka, Feign & Spring Cloud LoadBalancer - V2

Step 22 - Setting up Spring Cloud API Gateway

Step 23 - Enabling Discovery Locator with Eureka for Spring Cloud Gateway

Step 24 - Exploring Routes with Spring Cloud Gateway

Step 25 - Implementing Spring Cloud Gateway Logging Filter

Step 26 - Getting started with Circuit Breaker - Resilience4j

Step 27 - Playing with Resilience4j - Retry and Fallback Methods

Step 28 - Playing with Circuit Breaker Features of Resilience4j

Step 29 - Exploring Rate Limiting and BulkHead Features of Resilience4j - V2


Application	Port
-----------------
Limits Service	8080, 8081, ...

Spring Cloud Config Server	8888

Currency Exchange Service	8000, 8001, 8002, ..

Currency Conversion Service	8100, 8101, 8102, ...

Netflix Eureka Naming Server	8761

API Gateway Server	8765


URL and Response Structure
---------------------------
currency-exchange

`
{
   "id":10001,
   "from":"USD",
   "to":"INR",
   "conversionMultiple":65.00,
   "environment":"8000 instance-id"
}
`

currency-conversion

`
{
  "id": 10001,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65.00,
  "quantity": 10,
  "totalCalculatedAmount": 650.00,
  "environment": "8000 instance-id"
}
`

Application	URL
----------------
Spring Cloud Config Server	http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev

Currency Converter Service - Direct Call	http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10

Currency Converter Service - Feign	http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000

Currency Exchange Service	http://localhost:8000/currency-exchange/from/EUR/to/INR  
http://localhost:8001/currency-exchange/from/USD/to/INR

Eureka	http://localhost:8761/

API Gateway URL

- http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion/currency-conversion-feign/from/USD/to/INR/quantity/10

Custom Routes

- http://localhost:8765/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10
