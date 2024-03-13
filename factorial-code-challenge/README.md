# Contact Management Application

This is a simple contact management application built with Kotlin and Spring Boot. It allows users to perform CRUD operations on contacts, with support for viewing edit history.

## Features

- **Create:** Add a new contact with first name, last name, email, and phone number.
- **Read:** Retrieve contact details by ID or get a list of all contacts.
- **Update:** Update existing contact information.
- **Delete:** Remove a contact from the system.
- **Edit History:** View the history of edits made to a contact.

## Technologies Used

- Kotlin
- Spring Boot
- Spring Data JPA
- Liquibase (for database migrations)
- H2 Database (for testing)
- JUnit 5, Mockito (for unit and integration testing)
- Testcontainers (for integration testing with a real database)
- Maven (for build and dependency management)

## Setup Instructions

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/contact-management.git
    ```

2. **Build the project:**

    ```bash
    cd contact-management
    mvn clean package
    ```

3. **Run the application:**

    ```bash
    java -jar target/contact-management-1.0.0.jar
    ```

4. **Access the application:**

   Open your web browser and go to [http://localhost:8080](http://localhost:8080)

## API Endpoints

- **POST /contacts:** Create a new contact.
- **GET /contacts/{id}:** Retrieve contact details by ID.
- **PUT /contacts/{id}:** Update an existing contact.
- **DELETE /contacts/{id}:** Delete a contact.
- **GET /contacts:** Get a list of all contacts.

## Testing

The project includes unit tests and integration tests to ensure the reliability and correctness of the application. To run the tests:

```bash
mvn test
```

## Contributing

Contributions are welcome! If you find any bugs or want to add new features, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License.

