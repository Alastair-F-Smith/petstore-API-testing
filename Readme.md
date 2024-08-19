# Pet store testing

A project to test functionality of the [pet store API](https://petstore3.swagger.io). Currently, runners endpoints for
finding and adding pets as well as for creating users and logging in.

The purpose of this repository is primarily to gain experience working with API testing tools in Java. As such a third-party API is being used to develop runners against. This third-party API does not necessarily adhere to best-practices in API development and responses returned by the API are not necessarily consistent with the specifications in its Swagger documentation. To keep the focus on developing runners, the runners in this repository verify the API's behaviour as-is, rather than as specified. 

## Getting Started

### Prerequisites

* Java 21
* Intellij is the recommended IDE for this project with the plugins Cucumber for Java and Gherkin
* Maven

### Installation

Clone the repository and open in your preferred IDE. Install the dependencies from the pom.xml using maven.

## Usage

Tests can be run within an IDE by running the Cucumber runner class in `src/test/java/runners`. Alternatively, they can be
run by executing `mvn clean test` in the command line. Either way, running the runners will generate JSON- and
HTML-formatted reports in the `target` directory.

## Tested Endpoints
- Pet
  - POST /pet
  - GET /pet/findByStatus
  - GET /pet/{petId}
  - PUT /pet
  - POST /pet/{petId}
- User
  - POST /user
  - GET /user/login
  - GET /user/logout
  - GET /user/{username}