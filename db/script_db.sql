-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema books
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema books
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `books` DEFAULT CHARACTER SET utf8 ;
USE `books` ;

-- -----------------------------------------------------
-- Table `books`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`book` (
  `id_book` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `author` VARCHAR(50) NULL DEFAULT NULL,
  `price` DECIMAL(2,0) NULL DEFAULT NULL,
  `count_copies` INT(11) NULL DEFAULT NULL,
  `count_pages` INT(11) NULL DEFAULT NULL,
  `date_arrival` DATE NULL DEFAULT NULL,
  `date_cancellation` DATE NULL DEFAULT NULL,
  `date_publishing` DATE NULL DEFAULT NULL,
  `name_publishing` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_book`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `books`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`category` (
  `id_category` INT(11) NOT NULL AUTO_INCREMENT,
  `type_category` VARCHAR(255) NOT NULL,
  `genre` VARCHAR(30) NOT NULL,
  `language` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_category`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `books`.`book_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`book_category` (
  `book_category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `book_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  PRIMARY KEY (`book_category_id`),
  INDEX `category_id_fk_idx` (`category_id` ASC),
  INDEX `book_fk_idx` (`book_id` ASC),
  CONSTRAINT `book_fk`
    FOREIGN KEY (`book_id`)
    REFERENCES `books`.`book` (`id_book`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `category_id_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `books`.`category` (`id_category`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `books`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`role` (
  `id_role` INT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `books`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`user` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NULL DEFAULT NULL,
  `surname` VARCHAR(25) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `sex` VARCHAR(8) NULL DEFAULT NULL,
  `address` VARCHAR(50) NULL DEFAULT NULL,
  `phone` VARCHAR(15) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `books`.`user_books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`user_books` (
  `id_user_book` INT(11) NOT NULL AUTO_INCREMENT,
  `book_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id_user_book`),
  INDEX `book_id_fk_idx` (`book_id` ASC),
  INDEX `user_id_fk_idx` (`user_id` ASC),
  CONSTRAINT `book_id_fk`
    FOREIGN KEY (`book_id`)
    REFERENCES `books`.`book` (`id_book`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `books`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `books`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`user_role` (
  `id_user_role` INT NOT NULL,
  `role_id_role` INT(11) NOT NULL,
  `user_id_user` INT(11) NOT NULL,
  PRIMARY KEY (`id_user_role`),
  INDEX `fk_user_role_role1_idx` (`role_id_role` ASC),
  INDEX `fk_user_role_user1_idx` (`user_id_user` ASC),
  CONSTRAINT `fk_user_role_role1`
    FOREIGN KEY (`role_id_role`)
    REFERENCES `books`.`role` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_user1`
    FOREIGN KEY (`user_id_user`)
    REFERENCES `books`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
