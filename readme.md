## The Virtual Power Plant System API

## Description

    The Virtual Power Plant System API is a Spring Boot application designed to aggregate distributed power sources
    into a single cloud-based energy provider. It provides a RESTful API with the following key functionality:

## Features:

    1 Add Batteries Endpoint
    HTTP Method: POST
    Endpoint: /api/power-source/add
    Description: This endpoint accepts a list of batteries in the HTTP request body. Each battery must include the following
    information:
    name, postcode, and watt capacity. The provided data is then persisted in a database. (In-memory database is used for
    this implementation.).

    2 Get Batteries by Postcode Range Endpoint
    HTTP Method: POST
    Endpoint: /api/power-source/batteries
    Description: This endpoint receives a postcode range as parameters and returns a response containing a list of battery names
    that fall within the specified postcode range, sorted alphabetically. Additionally, the response includes statistical information
    about the returned batteries, such as the total and average watt capacity.

## Implementation Highlights:

    The project is implemented in Java using the Spring Boot framework.
    Java streams are leveraged to process and manipulate data efficiently.
    The API is designed to be clean, well-structured, and maintainable.
    Unit tests have been created to ensure code reliability and correctness.
    An in-memory database is used for data persistence, providing a lightweight and easily deployable solution.

## Tools and Libraries Used

    Spring boot 3.1.1  
    Java 17
    Swagger for API documentation
    H2 Database

## Table of Contents

    Project Name
    Description
    Tools and Libraries Used
    Table of Contents
    Getting Started
    Prerequisites
    Installation
    Usage
    API Documentation
    Author

## Getting Started

    1 Prerequisites

        Ensure that you have Git installed on your local machine. You can download it from the official
        website (https://git-scm.com/downloads).
        Make sure you have Java and Maven installed since Spring Boot projects are typically built using Maven.

    2 Installation

        Clone the repository:
        Run the following command to clone the GitHub repository:
        git clone https://github.com/yourusername/your-repo.git
        cd your-repo
    
    3 Build the project:

        Once the repository is cloned, navigate to the project directory using the terminal:
        cd spring-boot-project
    
        Build the project using Maven. Run the following command:
        mvn clean install
        
        After the build is successful, you can run the Spring Boot application using the following command:
        java -jar target/application_name.jar
        
        Once the application is running, you can access it by opening a web browser and navigating to the appropriate URL.
        The application's URL will depend on the specific Spring Boot project you cloned.

## API Documentation**

    You can access the API documentation by navigating to the following URL in your web browser:
    http://localhost:8083/swagger-ui/index.html or http:194.233.65.212:8083/swagger-ui/index.html(deployed)
    Swagger UI - This page allows you to explore and test the API endpoints.

## Author

    Dilip Babu Acharya 
    dilipbabuacharya2016@gmail.com
    Kathmandu, Nepal

