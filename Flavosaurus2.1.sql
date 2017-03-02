-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema flavosaurus2_1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema flavosaurus2_1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `flavosaurus2_1` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema flavosaurusdb
-- -----------------------------------------------------
USE `flavosaurus2_1` ;

-- -----------------------------------------------------
-- Table `flavosaurus2_1`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flavosaurus2_1`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `ROLE` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Create_Date` DATETIME NOT NULL,
  `Tagline` VARCHAR(200) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flavosaurus2_1`.`Ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flavosaurus2_1`.`Ingredient` (
  `Id` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `CrossCount_id` INT NOT NULL,
  `Type` VARCHAR(45) NULL,
  `Cook_Method` VARCHAR(45) NULL,
  `Sweet` TINYINT(11) NULL,
  `Sour` TINYINT(11) NULL,
  `Bitter` TINYINT(11) NULL,
  `Salt` TINYINT(11) NULL,
  `Umami` TINYINT(11) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flavosaurus2_1`.`RatingComment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flavosaurus2_1`.`RatingComment` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `User_id` INT NOT NULL,
  `Ingredient_id` INT NOT NULL,
  `Create_Date` DATETIME NOT NULL,
  `Cook_Method` VARCHAR(45) NULL,
  `Sour` VARCHAR(45) NULL,
  `Sweet` VARCHAR(45) NULL,
  `Bitter` VARCHAR(45) NULL,
  `Salt` VARCHAR(45) NULL,
  `Umami` VARCHAR(45) NULL,
  `Comment` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_RatingComment_User1_idx` (`User_id` ASC),
  INDEX `fk_RatingComment_Ingredient1_idx` (`Ingredient_id` ASC),
  CONSTRAINT `fk_RatingComment_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `flavosaurus2_1`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RatingComment_Ingredient1`
    FOREIGN KEY (`Ingredient_id`)
    REFERENCES `flavosaurus2_1`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flavosaurus2_1`.`Recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flavosaurus2_1`.`Recipe` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `User_id` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Create_Date` DATETIME NOT NULL,
  `Update_Date` DATETIME NOT NULL,
  `Procedure` VARCHAR(1200) NOT NULL,
  `Tools_Needed` VARCHAR(350) NULL,
  `Time_Needed` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Recipe_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Recipe_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `flavosaurus2_1`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flavosaurus2_1`.`CrossCount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flavosaurus2_1`.`CrossCount` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Ingredient_one` INT NOT NULL,
  `Ingredient_two` INT NOT NULL,
  `Count` INT NOT NULL,
  INDEX `fk_Ingredient_has_Ingredient_Ingredient2_idx` (`Ingredient_two` ASC),
  INDEX `fk_Ingredient_has_Ingredient_Ingredient1_idx` (`Ingredient_one` ASC),
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_Ingredient_has_Ingredient_Ingredient1`
    FOREIGN KEY (`Ingredient_one`)
    REFERENCES `flavosaurus2_1`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ingredient_has_Ingredient_Ingredient2`
    FOREIGN KEY (`Ingredient_two`)
    REFERENCES `flavosaurus2_1`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flavosaurus2_1`.`Recipe_has_Ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flavosaurus2_1`.`Recipe_has_Ingredient` (
  `Recipe_Id` INT NOT NULL,
  `Ingredient_Id` INT NOT NULL,
  PRIMARY KEY (`Recipe_Id`, `Ingredient_Id`),
  INDEX `fk_Recipe_has_Ingredient_Ingredient1_idx` (`Ingredient_Id` ASC),
  INDEX `fk_Recipe_has_Ingredient_Recipe1_idx` (`Recipe_Id` ASC),
  CONSTRAINT `fk_Recipe_has_Ingredient_Recipe1`
    FOREIGN KEY (`Recipe_Id`)
    REFERENCES `flavosaurus2_1`.`Recipe` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recipe_has_Ingredient_Ingredient1`
    FOREIGN KEY (`Ingredient_Id`)
    REFERENCES `flavosaurus2_1`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
