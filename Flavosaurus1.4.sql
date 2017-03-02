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
  `UserId` INT NOT NULL,
  `IngredientId` INT NOT NULL,
  `CreateDate` DATETIME NOT NULL,
  `UpdateDate` DATETIME NOT NULL,
  `Procedure` VARCHAR(1200) NULL,
  `ToolsNeeded` VARCHAR(350) NULL,
  `TimeNeeded` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Recipe_User1_idx` (`UserId` ASC),
  INDEX `fk_Recipe_Ingredient1_idx` (`IngredientId` ASC),
  CONSTRAINT `fk_Recipe_User1`
    FOREIGN KEY (`UserId`)
    REFERENCES `mydb`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recipe_Ingredient1`
    FOREIGN KEY (`IngredientId`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ingredient` (
  `Id` INT NOT NULL,
  `RecipeId` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Type` VARCHAR(45) NULL,
  `CookMethod` VARCHAR(45) NULL,
  `Sweet` TINYINT(11) NULL,
  `Sour` TINYINT(11) NULL,
  `Bitter` TINYINT(11) NULL,
  `Salt` TINYINT(11) NULL,
  `Umami` TINYINT(11) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Ingredient_Recipe1_idx` (`RecipeId` ASC),
  CONSTRAINT `fk_Ingredient_Recipe1`
    FOREIGN KEY (`RecipeId`)
    REFERENCES `mydb`.`Recipe` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RatingComment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RatingComment` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `IngredientId` INT NOT NULL,
  `UserId` INT NOT NULL,
  `IngredientId` VARCHAR(45) NOT NULL,
  `CreateDate` DATETIME NOT NULL,
  `CookMethod` TINYINT(11) NULL,
  `Sour` TINYINT(11) NULL,
  `Sweet` TINYINT(11) NULL,
  `Bitter` TINYINT(11) NULL,
  `Salt` TINYINT(11) NULL,
  `Umami` TINYINT(11) NULL,
  `Comment` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_RatingComment_User1_idx` (`UserId` ASC),
  INDEX `fk_RatingComment_Ingredient1_idx` (`IngredientId` ASC),
  CONSTRAINT `fk_RatingComment_User1`
    FOREIGN KEY (`UserId`)
    REFERENCES `mydb`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RatingComment_Ingredient1`
    FOREIGN KEY (`IngredientId`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CrossCount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CrossCount` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Ingredient1Id` INT NOT NULL,
  `Ingredient2Id` INT NOT NULL,
  `Count` INT NOT NULL,
  INDEX `fk_Ingredient_has_Ingredient_Ingredient2_idx` (`Ingredient2Id` ASC),
  INDEX `fk_Ingredient_has_Ingredient_Ingredient1_idx` (`Ingredient1Id` ASC),
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_Ingredient_has_Ingredient_Ingredient1`
    FOREIGN KEY (`Ingredient1Id`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ingredient_has_Ingredient_Ingredient2`
    FOREIGN KEY (`Ingredient2Id`)
    REFERENCES `mydb`.`Ingredient` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
