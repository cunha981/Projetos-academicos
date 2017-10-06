-- MySQL Script generated by MySQL Workbench
-- 05/11/16 14:11:54
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema niveis_abastecimento
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema niveis_abastecimento
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `niveis_abastecimento` DEFAULT CHARACTER SET utf8 ;
USE `niveis_abastecimento` ;

-- -----------------------------------------------------
-- Table `niveis_abastecimento`.`sistemas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `niveis_abastecimento`.`sistemas` (
  `idsistemas` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descr` VARCHAR(400) NOT NULL,
  `capacidade` DOUBLE NOT NULL,
  PRIMARY KEY (`idsistemas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `niveis_abastecimento`.`niveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `niveis_abastecimento`.`niveis` (
  `idniveis` INT NOT NULL AUTO_INCREMENT,
  `volume_diario` DOUBLE NOT NULL,
  `data` DATETIME NOT NULL,
  `sistemas_idsistemas` INT NOT NULL,
  PRIMARY KEY (`idniveis`),
  INDEX `fk_niveis_sistemas_idx` (`sistemas_idsistemas` ASC),
  CONSTRAINT `fk_niveis_sistemas`
    FOREIGN KEY (`sistemas_idsistemas`)
    REFERENCES `niveis_abastecimento`.`sistemas` (`idsistemas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `niveis_abastecimento`.`municipios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `niveis_abastecimento`.`municipios` (
  `idmunicipios` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `regiao` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idmunicipios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `niveis_abastecimento`.`municipios_has_sistemas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `niveis_abastecimento`.`municipios_has_sistemas` (
  `municipios_idmunicipios` INT NOT NULL,
  `sistemas_idsistemas` INT NOT NULL,
  PRIMARY KEY (`municipios_idmunicipios`, `sistemas_idsistemas`),
  INDEX `fk_municipios_has_sistemas_sistemas1_idx` (`sistemas_idsistemas` ASC),
  INDEX `fk_municipios_has_sistemas_municipios1_idx` (`municipios_idmunicipios` ASC),
  CONSTRAINT ``
    FOREIGN KEY (`municipios_idmunicipios`)
    REFERENCES `niveis_abastecimento`.`municipios` (`idmunicipios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_municipios_has_sistemas_sistemas1`
    FOREIGN KEY (`sistemas_idsistemas`)
    REFERENCES `niveis_abastecimento`.`sistemas` (`idsistemas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
