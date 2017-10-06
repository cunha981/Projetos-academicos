package br.com.scnc.service;

import java.util.List;

import br.com.scnc.bean.Projeto;
import br.com.scnc.dao.ProjetoDAO;

public class ProjetoService {

	private ProjetoDAO projetoDAO = new ProjetoDAO();

	public void salvar(Projeto projeto) {
		if (projeto.getId() == null)
			projetoDAO.insert(projeto);
		else
			projetoDAO.atualizar(projeto);
	}

	public List<Projeto> consultarDetalhes(Integer idOS) {
		List<Projeto> projetos = projetoDAO.findByIdOS(idOS);
		return projetos;
	}

	public List<Projeto> consultarDetalhesVol(Integer idVol) {
		List<Projeto> projetos = projetoDAO.findByIdVol(idVol);
		return projetos;
	}

	public Projeto consultar(Integer idProjeto) {
		Projeto projeto = projetoDAO.findById(idProjeto);

		return projeto;
	}

	public String excluir(Integer idProjeto) {
		return projetoDAO.excluir(idProjeto);

	}

	public void suspendeProjeto(Integer idOS) {
		projetoDAO.suspendeProjeto(idOS);

	}

	public List<Projeto> listaProjetoVaga() {
		return projetoDAO.listaProjetoVaga();
	}

	public void associarVoluntario(Integer idProjeto, Integer idVoluntario) {
		projetoDAO.associarVoluntario(idProjeto, idVoluntario);

	}

	public List<Projeto> listaProjeto() {
		
		return projetoDAO.listaProjetos();
	}

	public List<Projeto> listaProjetoVoluntario(Integer idVoluntario) {
		return projetoDAO.listaProjetoVoluntario(idVoluntario);
	}

	public List<Projeto> projetoCompativel(Integer id, Integer idVoluntario, String filtro) {
		return projetoDAO.projetoCompativel(id, idVoluntario, filtro);
	}

	public Integer countVoluntarios(Integer id) {
		return projetoDAO.countVoluntarios(id);
	}
}
