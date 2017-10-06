ALTER TABLE `Projeto2017`.`tbOS` 
ADD COLUMN `status` TINYINT(4) NOT NULL DEFAULT 1 ;

CREATE TABLE `Projeto2017`.`tbProjetoVoluntario` (
  `idProjeto` INT NOT NULL,
  `idVoluntario` INT NOT NULL,
  PRIMARY KEY (`idProjeto`, `idVoluntario`),
  INDEX `fk_tbVoluntario_has_tbProjeto_tbVoluntario2_idx` (`idVoluntario` ASC),
  CONSTRAINT `fk_tbVoluntario_has_tbProjeto_tbVoluntario2`
    FOREIGN KEY (`idVoluntario`)
    REFERENCES `Projeto2017`.`tbVoluntario` (`idVoluntario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbVoluntario_has_tbProjeto_tbProjeto2`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `Projeto2017`.`tbProjeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `Projeto2017`.`tbVoluntario` 
ADD COLUMN `status` TINYINT(4) NOT NULL DEFAULT 1;

ALTER TABLE `Projeto2017`.`tbVoluntarioProjeto` 
ADD COLUMN `status` TINYINT(4) NOT NULL DEFAULT 1;
