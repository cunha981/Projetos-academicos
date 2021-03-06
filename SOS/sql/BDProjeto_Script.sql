-- MySQL Script generated by MySQL Workbench
-- 04/30/17 20:06:13
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Projeto2017
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Projeto2017
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Projeto2017` DEFAULT CHARACTER SET utf8 ;
USE `Projeto2017` ;

-- -----------------------------------------------------
-- Table `Projeto2017`.`tbEndereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbEndereco` (
  `idEndereco` INT NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(200) NULL,
  `numero` INT NULL,
  `cidade` VARCHAR(100) NULL,
  `cep` VARCHAR(10) NOT NULL,
  `estado` VARCHAR(100) NULL,
  PRIMARY KEY (`idEndereco`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbOS` (
  `idOS` INT NOT NULL AUTO_INCREMENT,
  `nm_instituicao` VARCHAR(200) NOT NULL,
  `nm_fantasia` VARCHAR(200) NOT NULL,
  `cnpj` VARCHAR(20) NOT NULL,
  `idEndereco` INT NOT NULL,
  `tel` VARCHAR(15) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `dt_inicio` DATE NULL,
  PRIMARY KEY (`idOS`),
  INDEX `fk_tbOS_tbEndereco1_idx` (`idEndereco` ASC),
  CONSTRAINT `fk_tbOS_tbEndereco1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `Projeto2017`.`tbEndereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbAreaAtuacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbAreaAtuacao` (
  `idAreaAtuacao` INT NOT NULL AUTO_INCREMENT,
  `desc_areaAtuacao` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idAreaAtuacao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbProjeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbProjeto` (
  `idProjeto` INT NOT NULL AUTO_INCREMENT,
  `nm_projeto` VARCHAR(100) NOT NULL,
  `desc_projeto` VARCHAR(500) NOT NULL,
  `idOS` INT NOT NULL,
  `qtd_voluntario` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `dt_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_periodo_inscricao` DATE NOT NULL,
  `dt_inicio` DATE NULL,
  `idAreaAtuacao` INT NOT NULL,
  PRIMARY KEY (`idProjeto`),
  INDEX `fk_tbProjeto_tbOS1_idx` (`idOS` ASC),
  INDEX `fk_tbProjeto_tbAreaAtuacao1_idx` (`idAreaAtuacao` ASC),
  CONSTRAINT `fk_tbProjeto_tbOS1`
    FOREIGN KEY (`idOS`)
    REFERENCES `Projeto2017`.`tbOS` (`idOS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbProjeto_tbAreaAtuacao1`
    FOREIGN KEY (`idAreaAtuacao`)
    REFERENCES `Projeto2017`.`tbAreaAtuacao` (`idAreaAtuacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbDisponibilidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbDisponibilidade` (
  `idDisponibilidade` INT NOT NULL AUTO_INCREMENT,
  `periodo` VARCHAR(45) NOT NULL,
  `dia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDisponibilidade`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbVoluntario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbVoluntario` (
  `idVoluntario` INT NOT NULL AUTO_INCREMENT,
  `nm_voluntario` VARCHAR(45) NOT NULL,
  `tel` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `dt_nasc` DATE NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `estado_civil` VARCHAR(45) NOT NULL,
  `idEndereco` INT NOT NULL,
  PRIMARY KEY (`idVoluntario`),
  INDEX `fk_tbVoluntario_tbEndereco1_idx` (`idEndereco` ASC),
  CONSTRAINT `fk_tbVoluntario_tbEndereco1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `Projeto2017`.`tbEndereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbVoluntarioProjeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbVoluntarioProjeto` (
  `idVoluntario` INT NOT NULL,
  `idProjeto` INT NOT NULL,
  PRIMARY KEY (`idVoluntario`, `idProjeto`),
  INDEX `fk_tbVoluntario_has_tbProjeto_tbProjeto1_idx` (`idProjeto` ASC),
  INDEX `fk_tbVoluntario_has_tbProjeto_tbVoluntario1_idx` (`idVoluntario` ASC),
  CONSTRAINT `fk_tbVoluntario_has_tbProjeto_tbVoluntario1`
    FOREIGN KEY (`idVoluntario`)
    REFERENCES `Projeto2017`.`tbVoluntario` (`idVoluntario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbVoluntario_has_tbProjeto_tbProjeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `Projeto2017`.`tbProjeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbAreaAtuacaoVoluntario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbAreaAtuacaoVoluntario` (
  `idAreaAtuacao` INT NOT NULL,
  `idVoluntario` INT NOT NULL,
  PRIMARY KEY (`idAreaAtuacao`, `idVoluntario`),
  INDEX `fk_tbAreaAtuacao_has_tbVoluntario_tbVoluntario1_idx` (`idVoluntario` ASC),
  INDEX `fk_tbAreaAtuacao_has_tbVoluntario_tbAreaAtuacao1_idx` (`idAreaAtuacao` ASC),
  CONSTRAINT `fk_tbAreaAtuacao_has_tbVoluntario_tbAreaAtuacao1`
    FOREIGN KEY (`idAreaAtuacao`)
    REFERENCES `Projeto2017`.`tbAreaAtuacao` (`idAreaAtuacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbAreaAtuacao_has_tbVoluntario_tbVoluntario1`
    FOREIGN KEY (`idVoluntario`)
    REFERENCES `Projeto2017`.`tbVoluntario` (`idVoluntario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbDisponibilidadeVoluntario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbDisponibilidadeVoluntario` (
  `idDisponibilidade` INT NOT NULL,
  `idVoluntario` INT NOT NULL,
  PRIMARY KEY (`idDisponibilidade`, `idVoluntario`),
  INDEX `fk_tbDiaDisponivel_has_tbVoluntario_tbVoluntario1_idx` (`idVoluntario` ASC),
  INDEX `fk_tbDiaDisponivel_has_tbVoluntario_tbDiaDisponivel1_idx` (`idDisponibilidade` ASC),
  CONSTRAINT `fk_tbDiaDisponivel_has_tbVoluntario_tbDiaDisponivel1`
    FOREIGN KEY (`idDisponibilidade`)
    REFERENCES `Projeto2017`.`tbDisponibilidade` (`idDisponibilidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbDiaDisponivel_has_tbVoluntario_tbVoluntario1`
    FOREIGN KEY (`idVoluntario`)
    REFERENCES `Projeto2017`.`tbVoluntario` (`idVoluntario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projeto2017`.`tbDisponibilidadeProjeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projeto2017`.`tbDisponibilidadeProjeto` (
  `idDisponibilidade` INT NOT NULL,
  `idProjeto` INT NOT NULL,
  PRIMARY KEY (`idDisponibilidade`, `idProjeto`),
  INDEX `fk_tbDiaDisponivel_has_tbProjeto_tbProjeto1_idx` (`idProjeto` ASC),
  INDEX `fk_tbDiaDisponivel_has_tbProjeto_tbDiaDisponivel1_idx` (`idDisponibilidade` ASC),
  CONSTRAINT `fk_tbDiaDisponivel_has_tbProjeto_tbDiaDisponivel1`
    FOREIGN KEY (`idDisponibilidade`)
    REFERENCES `Projeto2017`.`tbDisponibilidade` (`idDisponibilidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbDiaDisponivel_has_tbProjeto_tbProjeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `Projeto2017`.`tbProjeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
