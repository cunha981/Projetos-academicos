package br.com.scnc.service;

import java.util.List;

import br.com.scnc.bean.AreaAtuacao;
import br.com.scnc.dao.AreaAtuacaoDAO;

public class AreaAtuacaoService {

	private AreaAtuacaoDAO areaAtuacaoDAO = new AreaAtuacaoDAO();
	
	public List<AreaAtuacao> listaAreaAtucaoVoluntario(Integer idVoluntario) {
		return areaAtuacaoDAO.listaAreaAtucaoVoluntario(idVoluntario);
	}

}
