# Bookstore Application

This is a small application to demonstrate how to build a containerised application with a database and a Java API

## Setup
Firstly choose a username and password for your Postgres database. Write these in `db/postgres_user.txt` and `db/postgres_password.txt`.
Docker mounts these as files inside the containers to avoid revealing this data on stdout and stdin.
