# Factorial Code Challenge

## The challenge
I've decided to implement the Contacts Management use case where there should be a tool to manage contacts and that should have a history of the changes.
- I started with the Backend where I implmented web service in Kotlin with Spring Boot that exposes the needed APIs (check more details in the `factorial-code-challenge` Readme)
- It is very basic due to time constraints but I believe it covers the main use cases
- I used a docker compose plugin that starts up a docker image with a PostgresSQL database and creates the schema and add some dummy data
- Used Liquibase to control the database migrations
- I added integration tests to cover the main scenarios
- I implemented a React App that is the FrontEnd of the application (I decided to not cover with tests due to time constraints.)

## Prerequisites

Make sure you have Docker and Docker Compose installed on your machine. You can download and install Docker Desktop from [here](https://www.docker.com/products/docker-desktop).

## Getting Started

1. Build and start the Docker containers:
```bash
docker-compose up --build
```
This command will build the Docker images for the backend and frontend, create and start the containers for PostgreSQL, Spring Boot, and React.

## Accessing the Applications

- *Spring Boot Backend*: You can access the Spring Boot backend at http://localhost:8080. It exposes endpoints for interacting with the database.
- *React Frontend*: The React frontend will be accessible at http://localhost:3000 in your web browser. It provides a user interface for interacting with the backend APIs.

## Stopping the Containers
To stop the Docker containers, you can press Ctrl + C in the terminal where docker-compose is running. Alternatively, you can run the following command in the project directory:

```bash
docker-compose down
```

This will stop and remove the containers.

