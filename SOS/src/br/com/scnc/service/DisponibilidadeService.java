package br.com.scnc.service;

import java.util.List;

import br.com.scnc.bean.Disponibilidade;
import br.com.scnc.dao.DisponibilidadeDAO;

public class DisponibilidadeService {

	private DisponibilidadeDAO disponibilidadeDAO = new DisponibilidadeDAO();
	
	public List<Disponibilidade> listaDisponibilidadeVoluntario(Integer idVoluntario) {
		return disponibilidadeDAO.listaDisponibilidadeVoluntario(idVoluntario);
	}

}
