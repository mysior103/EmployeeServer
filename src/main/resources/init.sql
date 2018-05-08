CREATE SCHEMA `employee` ;

CREATE TABLE `employee`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
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

CREATE TABLE users
(
    iduser int PRIMARY KEY NOT NULL,
    userName varchar(45),
    password varchar(45)
);