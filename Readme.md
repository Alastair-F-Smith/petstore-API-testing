# Pet store testing

A project to test functionality of the [pet store API](https://petstore3.swagger.io). Currently, tests endpoints for
finding and adding pets as well as for creating users and logging in.

## Getting Started

### Prerequisites

* Java 21
* Intellij is the recommended IDE for this project with the plugins Cucumber for Java and Gherkin
* Maven

### Installation

Clone the repository and open in your preferred IDE. Install the dependencies from the pom.xml using maven.

## Usage

Tests can be run within an IDE by running the Cucumber runner class in `src/test/java/tests`. Alternatively, they can be
run by executing `mvn clean test` in the command line. Either way, running the tests will generate JSON- and
HTML-formatted reports in the `target` directory.

## Tested Endpoints
- Pet
  - POST /pet/{petId}
  - GET /pet/findByStatus
- User
  - POST /user
  - GET /user/login
  - GET /user/{username}