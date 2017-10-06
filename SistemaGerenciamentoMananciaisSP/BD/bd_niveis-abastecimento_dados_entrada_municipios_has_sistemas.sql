-- São Paulo
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('754', '1');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('754', '2');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('754', '3');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('754', '4');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('754', '5');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('754', '6');

-- Guarulhos
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('415', '1');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('415', '2');

INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('201', '1');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('202', '2');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('203', '3');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('204', '4');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('205', '5');
INSERT INTO `niveis_abastecimento`.`municipios_has_sistemas` (`municipios_idmunicipios`, `sistemas_idsistemas`) VALUES ('206', '6');

SELECT * FROM niveis_abastecimento.municipios WHERE nome = 'Guarulhos';
SELECT * FROM niveis_abastecimento.municipios_has_sistemas ORDER BY municipios_idmunicipios;