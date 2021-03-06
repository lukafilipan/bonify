-- MySQL Script generated by MySQL Workbench
-- 08/29/16 01:06:21
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema news
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema news
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `news` DEFAULT CHARACTER SET utf8 ;
USE `news` ;

-- -----------------------------------------------------
-- Table `news`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `news`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `news`.`server`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `news`.`server` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `news`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `news`.`article` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `created` DATETIME NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `server_id` BIGINT NOT NULL,
  `body` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_articles_servers_idx` (`server_id` ASC),
  CONSTRAINT `fk_articles_servers`
    FOREIGN KEY (`server_id`)
    REFERENCES `news`.`server` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `news`.`user_server`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `news`.`user_server` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `subscribed` BINARY NOT NULL,
  `user_id` BIGINT NOT NULL,
  `server_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_server_user1_idx` (`user_id` ASC),
  INDEX `fk_user_server_server1_idx` (`server_id` ASC),
  CONSTRAINT `fk_user_server_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `news`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_server_server1`
    FOREIGN KEY (`server_id`)
    REFERENCES `news`.`server` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `news`.`user_article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `news`.`user_article` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `read` BINARY NOT NULL,
  `user_id` BIGINT NOT NULL,
  `article_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_article_user1_idx` (`user_id` ASC),
  INDEX `fk_user_article_articles1_idx` (`article_id` ASC),
  CONSTRAINT `fk_user_article_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `news`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_article_articles1`
    FOREIGN KEY (`article_id`)
    REFERENCES `news`.`article` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
