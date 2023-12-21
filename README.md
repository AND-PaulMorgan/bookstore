# Bookstore Application

This is a small application to demonstrate how to build a containerised application with a database and a Java API

## Database Setup

Populate the following files in `db/secrets/` with a single string not terminated by a newline:
- `postgres_user` - username of the postgres admin user
- `postgres_password` - the admin user's password
- `postgres_db` - the name of the database that will hold book data

Docker mounts these as files inside the containers to avoid revealing this data on stdout and stdin. They are used by both the Postgres container to configure the database and the api container in the database connection string.

## API Setup

Populate these files in `bookstoreapi/secrets` with strings without trailing newlines:
- `api_username` - the username for basic auth purposes
- `api_password` - the password for basic auth
- `api_role` - the default role, should be `USER`

## Starting the Service

Run `docker-compose up` in the root of the repo. This will start a database and an api container.
The default user will be populated in an in-memory repository using the values above.

## Accessing the service
Currently through apostman collection. Details to be added in future.

## Endpoints
The CRUD enpoints are all implemented:
- `GET http://localhost:8080/book` - get all books
- `GET http://localhost:8080/book/{id}` - get a single book by id
- `POST http://localhost:8080/book` - creates a new book, fails if ISBN is already in use. (see sample CREATE BOOK request body below)
- `PUT http://localhost:8080/book/{id}` - update a book
- `DELETE http://localhost:8080/book/{id}` - removes a book (destructive - actual delete from database, not soft delete setting "isDeleted" to true)

Sample CREATE BOOK request body:
```
{
    "isbn": "978-1-119-86164-5",
    "title": "Java For Dummies, 8th Edition",
    "description": "Learn to write practical, reusable code with straight forward tutorials and tips in this newest edition of this For Dummies bestseller",
    "tags": [
        "Computer",
        "Tech"
    ],
    "authors": "Barry Burd",
    "releaseDate": "2022-03-16"
}
```
