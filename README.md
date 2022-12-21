# **spring_boot_simple_project**

## **Foreword**

The desire to understand how the Spring framework and AWS  service work and to implement the Spring Boot application on my own experience inspired me to write this project. During this project I have improved my skills in the following stack of technologies Java Core, SQL, Spring (MVC, Data, Security, Boot), Thyme Leaf, Gradle, Tomcat, Git, AWS.

## **Description**

This project implements a simple product management system (various products and their manufacturers).
This system assumes 2 types of users: normal user and administrator.
Ordinary users are provided with the following capabilities: 
- view lists of all products and manufacturers,
- search for products or manufacturers either by name or by ID number.

The administrator, in addition to these features, is given the following capabilities:
- add, edit and delete products and manufacturers; 
- Obtain a list of all users, search by name or ID number, the ability to add, edit and delete users; 
- get the list of all user roles, search by name or ID number, the ability to add, edit and delete roles.

## **How to use the project**

When you go to the project link (regardless of the endpoint), you will be redirected to a login page.
If this is the first time you enter the site, you will be prompted to register. In this case you will be automatically saved in the database with the role ROLE_USER, which implies that your possibilities are limited (see above).
To enter the project as an administrator use the following credentials:
- E-mail - admin@admin.com
- Password - adminpass.
In this case you will be able to reassign the roles of existing users or add new users with different roles.

## **How to deploy this project on a local computer**

Software requirements:
- Windows 10 or later,
- PostgreSQL 11 or later,
- Java 17 or later.

To deploy the project you will need to do the following steps:
1.	Clone the project from this repository to your computer.
2.	Create a PostgreSQL database (remember its name - it will necessary  later). The project will create the database structure automatically using ThymeLeaf migrations.
3.	In the application.properties file make the following changes.

 server.port=(your port)
    
  Where (your port) can be 8080, 5000 or another. But be sure that You'll enter into project from Your browser just from this port. 
  
 spring.datasource.url=jdbc:postgresql://localhost:(your port)/(your database)
    
  Where:
    (your port) - the port from which You connect with Your PostgreSQL database (created in the step#2), as usual 5432;
    (your database) - name of Your database created in the step#2.

 spring.datasource.username=(usernmame)
    
  Where (usernmame) is the username which You use to get access into PostgreSQL, as usual postgres.

 spring.datasource.password=(password)
    
  Where (password) the password which You use to get access into PostgreSQL

 spring.jpa.hibernate.ddl-auto=validate
    
This line don't change.

4.	Run the project in the IDEA like you normally do (green triangle in the AppApplication class).

5.	Then the database structure will be created and you need to populate it with the original values of roles and user types.
To do this, make queries in the database You created, which are contained in the following files, which are located in the root directory of the project:
	- populate_role.sql;
	- populate_user.sql.

Important: the order of the queries is important - first populate_role.sql, then populate_user.sql.

6.	In any browser go to http://localhost:(YourPort)

 Where (yourPort) is the port which You pointed in the first row of application.propertises (see step#3).
