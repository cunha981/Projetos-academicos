ALTER TABLE tbDisponibilidadeProjeto DROP FOREIGN KEY `fk_tbDiaDisponivel_has_tbProjeto_tbProjeto1`;

ALTER TABLE tbDisponibilidadeProjeto
   ADD CONSTRAINT `fk_tbDiaDisponivel_has_tbProjeto_tbProjeto1`
   FOREIGN KEY (`idProjeto` )
   REFERENCES `tbProjeto` (`idProjeto` )
   ON DELETE CASCADE;

ALTER TABLE tbOS DROP FOREIGN KEY `fk_tbOS_tbEndereco1`;

ALTER TABLE tbOS
   ADD CONSTRAINT `fk_tbOS_tbEndereco1`
   FOREIGN KEY (`idEndereco` )
   REFERENCES `tbEndereco` (`idEndereco` )
   ON DELETE CASCADE;