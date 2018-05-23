# Employee Server

[![Build Status](https://circleci.com/gh/mysior103/EmployeeServer.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/mysior103/EmployeeServer)
This is server for Employee application. This is a program for Advanced Programming in Java for my University.
***
## Prerequisites

For correct working, this application depends on MySQL server and EmployeeServer application.

For run this program you will need:

* Running docker container with mysql 8.0
* Run server - application EmployeeServer.
* Build and run rmiregistry.

***
## Installing

### Docker

MySQL server in 8.0 version is working in docker container.
In terminal type:
```
$ docker run --name employee-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql --default-authentication-plugin=mysql_native_password --character-set-server=latin1 --collation-server=latin1_swedish_ci
```

Read more about [Docker](https://docker.com)

### MySQL

You have to prepare MySQL Server. If Docker container is running, in terminal insert: 
`$ docker exec -it employee-mysql mysql -uroot -proot`

In MySQL server create schema:
```CREATE SCHEMA 'employee';```

Create employee table in Database:
```
CREATE TABLE `employee`.`employee` (
  `id` LONG NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  `salary` DECIMAL NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `commission` DECIMAL NULL,
  `maxCommission` DECIMAL NULL,
  `creditCardNumber` VARCHAR(45) NULL,
  `costLimit` DECIMAL NULL,
  PRIMARY KEY (`id`));
 ```
 
Create users table in Database:
```
CREATE TABLE `employee`.`users`(
    iduser int PRIMARY KEY NOT NULL,
    userName varchar(45),
    password varchar(45));
```

Create user for logging (default user name is user and password is password):
```
INSERT INTO `employee`.`users`(userName,password) VALUES('user','password');
```

### rmiregistry

You have to run rmiregistry to start RMI server. 

First build the project. Navigate to EmployeeServerv2 project, and in terminal run:
`$ gradle build`

Then naviage to \EmployeeServerv2\out\production\classes\ and run:
`$ rmic pl.mysior.Logging.LoggingInterfaceImpl`

Copy **LoggingInterfaceImpl_Stub.class** file from \EmployeeServerv2\out\production\classes\pl\mysior\Logging\ to EmployeeClient\out\production\classes\pl\mysior\

for Windows:
`> start rmiregistry`
for Linux\Mac:
`$ rmiregistry &`


# Run Application and Enjoy! 
***
#### Authors

* **Rafał Podleś** 

#### References

[EmployeeServer](https://github.com/mysior103/EmployeeServerv2)
