# RESTful-APIs
This project is a showcase of how to develop RESTful APIs using Spring Boot 3, Java 17, and Spring HATEOAS. The goal of the project is to demonstrate best practices for building a RESTful API that includes hyperlinks (HATEOAS - Hypermedia as the Engine of Application State) to enhance API discoverability and navigation.

## Introduction
RESTful APIs have become the standard for building web applications due to their simplicity and scalability. Spring Boot, a popular Java framework, provides an excellent platform for building robust and efficient RESTful APIs. In this project, we leverage the power of Java 17, Spring Boot 3, and Spring HATEOAS to create a RESTful API that follows the principles of HATEOAS.

## Features
- Demonstrates a basic RESTful API setup with Spring Boot 3.
- Utilizes Java 17 features for enhanced performance and maintainability.
- Implements HATEOAS to include hyperlinks in API responses for improved discoverability.
- Showcases best practices for structuring RESTful endpoints and request handling.

## Prerequisites
To run this project locally, make sure you have the following installed:

- Java 17 (or later)
- Maven
- Your favorite IDE (IntelliJ, Eclipse, etc.) with Spring Boot support

## Getting Started
1. Clone this repository to your local machine.
2. Open the project in your preferred IDE.
3. Build the project using Maven.
4. Run the Spring Boot application.
5. The API should now be accessible at `http://localhost:9090`

## Usage
The API consists of several endpoints that allow you to perform typical CRUD (Create, Read, Update, Delete) operations on resources. The API responses include hyperlinks, making it easier to navigate and discover related resources.

Here's a brief overview of the available endpoints:


`POST /api/customers`: Creates a new customer and returns its details with hyperlinks.

`Request`
```bash
curl -X POST -v http://localhost:9090/api/customers -H 'Content-Type: application/json' -d '{"id": "1", "status":"NEW", "firstName": "Samwise", "middleName": "Jane", "lastName": "Goat" }' | json_pp
```
`Response`
```bash
{
   "_links" : {
      "customers" : {
         "href" : "http://localhost:9090/api/customers"
      },
      "kyc" : {
         "href" : "http://localhost:9090/api/customers/1/kyc"
      },
      "self" : {
         "href" : "http://localhost:9090/api/customers/1"
      }
   },
   "firstName" : "Samwise",
   "id" : "1",
   "lastName" : "Goat",
   "middleName" : "Jane",
   "status" : "NEW"
}
```


`GET /api/customers/1`: Retrieves details of a specific customer with hyperlinks to related resources.

`Request`
```bash
curl -v http://localhost:9090/api/customers/1 | json_pp
```
`Response`
```bash
{
   "_links" : {
      "customers" : {
         "href" : "http://localhost:9090/api/customers"
      },
      "kyc" : {
         "href" : "http://localhost:9090/api/customers/1/kyc"
      },
      "self" : {
         "href" : "http://localhost:9090/api/customers/1"
      }
   },
   "firstName" : "Samwise",
   "id" : "1",
   "lastName" : "Goat",
   "middleName" : "Jane",
   "status" : "NEW"
}
```


`GET /api/customers`: Fetches a list of all customers with hyperlinks to individual customer details.

`Request`
```bash
curl -v http://localhost:9090/api/customers | json_pp
```
`Response`
```bash
{
   "_embedded" : {
      "customerList" : [
         {
            "_links" : {
               "customers" : {
                  "href" : "http://localhost:9090/api/customers"
               },
               "kyc" : {
                  "href" : "http://localhost:9090/api/customers/1/kyc"
               },
               "self" : {
                  "href" : "http://localhost:9090/api/customers/1"
               }
            },
            "firstName" : "Samwise",
            "id" : "1",
            "lastName" : "Goat",
            "middleName" : "Jane",
            "status" : "NEW"
         }
      ]
   },
   "_links" : {
      "self" : {
         "href" : "http://localhost:9090/api/customers"
      }
   }
}
```


`PUT /api/customers/1`: Updates a customer and returns its details with hyperlinks.

`Request`
```bash
curl -X PUT -v http://localhost:9090/api/customers/1 -H 'Content-Type: application/json' -d '{"id": "1", "status":"NEW", "firstName": "Samwise", "middleName": "Jane", "lastName": "Goatee" }' | json_pp
```
`Response`
```bash
{
   "_links" : {
      "customers" : {
         "href" : "http://localhost:9090/api/customers"
      },
      "kyc" : {
         "href" : "http://localhost:9090/api/customers/1/kyc"
      },
      "self" : {
         "href" : "http://localhost:9090/api/customers/1"
      }
   },
   "firstName" : "Samwise",
   "id" : "1",
   "lastName" : "Goatee",
   "middleName" : "Jane",
   "status" : "NEW"
}
```


`PUT /api/customers/1/kyc`: Updates a customer's status from NEW to KYC and returns its details with hyperlinks.

`Request`
```bash
curl -X PUT -v http://localhost:9090/api/customers/1/kyc -H 'Content-Type: application/json' -d '{"id": "1", "status":"NEW", "firstName": "Samwise", "middleName": "Jane", "lastName": "Goatee" }' | json_pp
```
`Response`
```bash
{
   "_links" : {
      "cancel" : {
         "href" : "http://localhost:9090/api/customers/1/cancel"
      },
      "complete" : {
         "href" : "http://localhost:9090/api/customers/1/complete"
      },
      "customers" : {
         "href" : "http://localhost:9090/api/customers"
      },
      "self" : {
         "href" : "http://localhost:9090/api/customers/1"
      }
   },
   "firstName" : "Samwise",
   "id" : "1",
   "lastName" : "Goatee",
   "middleName" : "Jane",
   "status" : "KYC"
}
```


`PUT /api/customers/1/complete`: Updates a customer's status from KYC to COMPLETED and returns its details with hyperlinks.

`Request`
```bash
curl -X PUT -v http://localhost:9090/api/customers/1/complete -H 'Content-Type: application/json' -d '{"id": "1", "status":"NEW", "firstName": "Samwise", "middleName": "Jane", "lastName": "Goatee" }' | json_pp
```
`Response`
```bash
{
   "_links" : {
      "customers" : {
         "href" : "http://localhost:9090/api/customers"
      },
      "self" : {
         "href" : "http://localhost:9090/api/customers/1"
      }
   },
   "firstName" : "Samwise",
   "id" : "1",
   "lastName" : "Goatee",
   "middleName" : "Jane",
   "status" : "COMPLETED"
}
```


`PUT /api/customers/1/cancel`: Updates a customer's status from KYC to CANCELLED and returns its details with hyperlinks.

`Request`
```bash
curl -X PUT -v http://localhost:9090/api/customers/2/cancel -H 'Content-Type: application/json' -d '{"id": "2", "firstName": "Jeff", "middleName": "Bezos", "lastName": "Goat" }' | json_pp
```
`Response`
```bash
{
   "_links" : {
      "customers" : {
         "href" : "http://localhost:9090/api/customers"
      },
      "self" : {
         "href" : "http://localhost:9090/api/customers/2"
      }
   },
   "firstName" : "Jeff",
   "id" : "2",
   "lastName" : "Goat",
   "middleName" : "Bezos",
   "status" : "CANCELLED"
}
```


`DELETE /api/customers/1`: Deletes a customer.

`Request`
```bash
curl -X DELETE -v http://localhost:9090/api/customers/1 | json_pp
```
`Response`
```bash
```

## License
This project is licensed under the MIT License, which means you can use, modify, and distribute it freely. See the LICENSE file for more details.
