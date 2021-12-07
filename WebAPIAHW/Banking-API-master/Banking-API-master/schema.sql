-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bank-api
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bank-api` ;

-- -----------------------------------------------------
-- Schema bank-api
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank-api` DEFAULT CHARACTER SET utf8 ;
USE `bank-api` ;

-- -----------------------------------------------------
-- Table `bank-api`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank-api`.`Customer` ;

CREATE TABLE IF NOT EXISTS `bank-api`.`Customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` LONGTEXT NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank-api`.`Account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank-api`.`Account` ;

CREATE TABLE IF NOT EXISTS `bank-api`.`Account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `accountNumber` MEDIUMTEXT NOT NULL,
  `sortCode` INT NOT NULL,
  `balance` DOUBLE NOT NULL,
  `ownerId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_owner_idx` (`ownerId` ASC),
  CONSTRAINT `owner`
  FOREIGN KEY (`ownerId`)
  REFERENCES `bank-api`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank-api`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank-api`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `bank-api`.`Transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `date` TIMESTAMP NULL DEFAULT NOW(),
  `amount` DOUBLE NOT NULL,
  `balance` DOUBLE NOT NULL,
  `accountId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_idx` (`accountId` ASC),
  CONSTRAINT `fk_account`
  FOREIGN KEY (`accountId`)
  REFERENCES `bank-api`.`Account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
