INSERT INTO sistemas VALUES(1, 'Cantareira', 'É o maior da Região Metropolitana de São Paulo. A capacidade da estação de tratamento é de 33 mil litros de água por segundo destinados a 6,5 milhões de pessoas das Zonas Norte, Central e partes das Zonas Leste e Oeste da capital, além de diversos outros municípios.', 982.07);
INSERT INTO sistemas VALUES(2, 'Alto Tietê', 'O sistema é formado por 10 rios, sendo Tietê, Biritiba e Jundiaí alguns deles. São tratados 15 mil litros de água por segundo para atender 4,5 bilhões de pessoas da Zona Leste da capital e de vários municípios como Arujá, Ferraz de Vasconcelos, Suzano, Mauá e Mogi das Cruzes.', 573.81);
INSERT INTO sistemas VALUES(3, 'Guarapiranga', 'É o segundo maior sistema da Região Metropolitana, composto pela Represa Guarapiranga (formada por diversos rios como Embu-Mirim e Embu-Guaçu) e pela Represa Billings (Rio Taquacetuba). Produz 15 mil litros de água por segundo e abastece 4,9 milhões de pessoas das Zonas Sul e Sudoeste da Capital.', 171.19);
INSERT INTO sistemas VALUES(4, 'Alto Cotia', 'A água vem da represa Pedro Beicht, formada pelos rios Capivari e Cotia do Peixe. A captação é feita na represa da Graça e transportada para a Estação de Tratamento Morro Grande. A produção de 1,2 mil litros de água por segundo abastece cerca de 410 mil habitantes dos municípios de Cotia, Embu, Itapecerica da Serra, Embu-Guaçu e Vargem Grande.', 16.50);
INSERT INTO sistemas VALUES(5, 'Rio Grande', 'É um braço da Represa Billings. Produz 5 mil litros de água por segundo e abastece 1,2 milhão de pessoas em Diadema, São Bernardo do Campo e parte de Santo André.', 112.18);
INSERT INTO sistemas VALUES(6, 'Rio Claro', 'Localizado a 70 km da Capital, produz 4 mil litros por segundo. A água vem do rio Ribeirão do Campo e é tratada na Estação Casa Grande. Abastece 1,5 milhão de pessoas do bairro de Sapopemba, na Capital, e parte dos municípios de Ribeirão Pires, Mauá e Santo André. O sistema foi construído na década de 30, e foi ampliado na década de 70.', 13.67);

SELECT * FROM sistemas;

/*

SELECT * FROM niveis;

SELECT volume_diario, data, si.nome 
	FROM sistemas si
		INNER JOIN niveis nv ON nv.idniveis = si.idsistemas
	WHERE idsistemas = 1;
    
SELECT volume_diario, data, sistemas_idsistemas FROM niveis WHERE sistemas_idsistemas = 1;

*/