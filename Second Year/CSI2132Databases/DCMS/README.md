# Dental Clinic Management System

# Requirements
- IntelliJ IDEA is highly recommended (other IDEs can also be used)
- JDK 17 (ensure that your JAVA_HOME environment variable is set to the path to your JDK 17 installation, or that your IDE is using JDK 17)
- Maven (should be included with IntelliJ IDEA and Eclipse)

# Setting Up the Database

The database to be used by the application needs to be set up locally.

1. Create a new database with PostgreSQL called "dcms"
2. Right click on the newly-created database's name, then click on "Query Tool"
3. Open the schema file of the database, then execute the script (F5)
4. Open the mock data file, then execute the script

# Setting Up an Account

Creating a new account in pgAdmin is highly recommended. Alternatively, refer to the "Modifying the Application Properties" section to change the
account details used to access the database.

1. In pgAdmin, right-click on Login/Group Roles, then click on Create > Login/Group Role
2. Set the name to be "test" in the General tab
3. In the Defintion tab, set the password to be "test
4. In the Privileges tab, turn on the following:
   - Can login?
   - Superuser?
   - Inherit rights from the parent roles?
5. In the Membership tab, assign the following roles to this user with admin privileges:
   - pg_read_all_data
   - pg_write_all_data
6. Save the new account

# Modifying the Application Properties

If another user account will be used to access the database, then the `application.properties` file in `src/main/resources` must be modified.

Set the username and password in the following lines:<br>
`spring.datasource.username=test`<br>
`spring.datasource.password=test`<br>

Ensure that modified versions this file are **NOT** commited to the repository, otherwise your user data will be exposed to everyone 
who has access to this repository.

# Running the Application

- If using a Java IDE, simply build and run the application
- Otherwise, run the application using the command line with `./mvnw spring-boot:run`
- Visit `http://localhost:8080/` to access the index page
- Visit `http://localhost:8080/login` to log in with an account in the "user_account" table of the database
