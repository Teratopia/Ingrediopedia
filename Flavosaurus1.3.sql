-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `ROLE` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `CreateDate` DATETIME NOT NULL,
  `Tagline` VARCHAR(200) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Recipe` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `User_id` INT NOT NULL,
  `Ingredient_Id` INT NOT NULL,
  `CreateDate` DATETIME NOT NULL,
  `UpdateDate` DATETIME NOT NULL,
  `Procedure` VARCHAR(1200) NOT NULL,
  `ToolsNeeded` VARCHAR(350) NULL,
  `TimeNeeded` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Recipe_User1_idx` (`User_id` ASC),
  INDEX `fk_Recipe_Ingredient1_idx` (`Ingredient_Id` ASC),
  CONSTRAINT `fk_Recipe_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recipe_Ingredient1`
    FOREIGN KEY (`Ingredient_Id`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ingredient` (
  `Id` INT NOT NULL,
  `Recipe_Id` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Type` VARCHAR(45) NULL,
  `CookMethod` VARCHAR(45) NULL,
  `Sweet` TINYINT(11) NULL,
  `Sour` TINYINT(11) NULL,
  `Bitter` TINYINT(11) NULL,
  `Salt` TINYINT(11) NULL,
  `Umami` TINYINT(11) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Ingredient_Recipe1_idx` (`Recipe_Id` ASC),
  CONSTRAINT `fk_Ingredient_Recipe1`
    FOREIGN KEY (`Recipe_Id`)
    REFERENCES `mydb`.`Recipe` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RatingComment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RatingComment` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `User_id` INT NOT NULL,
  `Ingredient_id` INT NOT NULL,
  `UserId` INT NOT NULL,
  `IngredientId` VARCHAR(45) NOT NULL,
  `CreateDate` DATETIME NOT NULL,
  `CookMethod` VARCHAR(45) NULL,
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
    REFERENCES `mydb`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RatingComment_Ingredient1`
    FOREIGN KEY (`Ingredient_id`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CrossCount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CrossCount` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Ingredient1` INT NOT NULL,
  `Ingredient2` INT NOT NULL,
  `Count` INT NOT NULL,
  INDEX `fk_Ingredient_has_Ingredient_Ingredient2_idx` (`Ingredient2` ASC),
  INDEX `fk_Ingredient_has_Ingredient_Ingredient1_idx` (`Ingredient1` ASC),
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_Ingredient_has_Ingredient_Ingredient1`
    FOREIGN KEY (`Ingredient1`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ingredient_has_Ingredient_Ingredient2`
    FOREIGN KEY (`Ingredient2`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
