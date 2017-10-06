package br.com.scnc.service;

import br.com.scnc.bean.Endereco;
import br.com.scnc.dao.EnderecoDAO;

public class EnderecoService {
	EnderecoDAO enderecoDAO = new EnderecoDAO();

	public Endereco salvar(Endereco endereco) {
		if(endereco.getId() == null)
			return enderecoDAO.insert(endereco);
		
		return enderecoDAO.atualizar(endereco);
	}
}
