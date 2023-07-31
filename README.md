# About the project

A Java-based application for managing martial arts trainings and exams.
This project provides a flexible and scalable solution for users to manage booking trainings/exams and tracking their own activities along with the dojo's activities.

# Technologies used

> - **Java Spring**: For backend it is a popular choice among developers in both commercial and personal use.
> It also has a lot of history, so there are already answered questions for it.
> - **PostgreSQL** for the database: The dataflow revolves around connections so a relational database seems to fit the case.
> I also have the most experience with PostgreSQL, and Java Spring has an adapter for it as well.

# Install

> ## Build:
> 1. Setup the proper environment variables 
>   - 'JWT_SECRET' 
>     - 512bit Hex Key
>   - 'DB_PASSWORD'
>     - Always use strong production password
>   - 'DB_USER'
>     - Always use non-standard username
> 2. Navigate to the root folder, where pom.xml is
> 3. Run: ``` mvn -B package -DskipITs --file pom.xml ```

> ## Run application:
> After the Build process run: ``` java -jar target/budoka-0.0.1-SNAPSHOT.jar ``` in the root folder.

# Usage

> The application currently doesn't have a frontend. You can try out the application by sending http requests to the backend e.g by Postman or Insomnia.
> A registration POST request structure looks like this:
```
{
    "name":"Master Aikido",
    "email":"mail@mail.com",
    "password":"pw",
    "role":"OTHER"
}
```
> A login POST request structure looks like this:
```
{
    "email":"mail@mail.com",
    "password":"pw"
}
```

# *** Features are comming soon! *** #
