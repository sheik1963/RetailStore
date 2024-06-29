# Retail Store Application

This project is a Spring Boot application designed to simulate a retail store. It allows users to add products to their cart and calculates discounts based on certain conditions.

## Features

- Users can have different roles (employee, affiliate, customer).
- Users can add products to their cart.
- The application calculates discounts based on the user's role and the nature of the products in the cart.

## Discount Rules

1. If the user is an employee of the store, they get a 30% discount.
2. If the user is an affiliate of the store, they get a 10% discount.
3. If the user has been a customer for over 2 years, they get a 5% discount.
4. For every $100 on the bill, there is a flat $5 discount for all the customers
6. A user can get only one of the percentage-based discounts on a bill.

## Model Class
- **User**: Represents a user in the system.
- **Product**: Represents a products.

##Controller
- **BillController** : Recevice post request from user for bill generation.

##Service
- **BillService** : Calculate the discount based on user type. 

##Code Coverage
- 100% Code Coverage for business logic BillService



## Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6.3 or higher

###Maven Install

####To build and install the project using Maven, follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project where `pom.xml` is located.
3. Run the following Maven command:

   ```bash
   mvn clean install
   ```

####To generate Jacoco code coverage report use test Maven, 
- HTML Report Generated in target\site\jacoco\index.html

```bash
   mvn test
```

#####To Generating SonarQube

### Prerequisites

- SonarQube/Ensure you have SonarQube configured and accessible.

### Running Sonar Analysis

1. **Open Terminal or Command Prompt**: Navigate to the root directory of your project where `pom.xml` (for Maven) or `build.gradle` (for Gradle) is located.
2. Sonar Report http://localhost:9000 with the URL of your SonarQube server

   ```bash
   mvn sonar:sonar 
   ```

###Running the Application
- Use Maven to run the Spring Boot application
- Application URL http://localhost:8082/

    ```bash
   mvn spring-boot:run
    ```

###API Reqeust & EndPoint

1.Discount calculated by userType for non groscries items
   - employee(30% Discount)
   - affiliated(10% Discount)
   - customer registered before 2 year(5% discount)

2.Flat 5% in total for all users.

          EndPoint : http://localhost:8082/generate/bill
          HttpMethod : Post
          RequestBody JSON
    
          {
          "userId": "1234",
          "userType": "employee",
          "registeredDate": "2022-05-12",
          "shoppingCart": [
              {
                  "productName": "dal",
                  "isGrocery": true,
                  "amount": 99,
                  "unit": 2
              },
              {
                  "productName": "shapoo",
                  "isGrocery": false,
                  "amount": 500,
                  "unit": 2
              }
          ]
        }


###UML Diagarm
- **FlowChart & Sequence Diagram** avaliable in resource\uml folder

