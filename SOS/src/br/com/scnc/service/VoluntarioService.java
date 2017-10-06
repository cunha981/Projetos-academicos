package br.com.scnc.service;

import java.util.List;

import br.com.scnc.bean.Endereco;
import br.com.scnc.bean.Projeto;
import br.com.scnc.bean.Voluntario;
import br.com.scnc.dao.VoluntarioDAO;

public class VoluntarioService {
	private EnderecoService enderecoService = new EnderecoService();
	private VoluntarioDAO dao = new VoluntarioDAO();
	private ProjetoService projetoService = new ProjetoService();

	public Integer inserir(Voluntario voluntario) {
		return dao.inserir(voluntario);
	}

	public void atualizar(Voluntario voluntario) {
		dao.atualizar(voluntario);
	}

	public void excluir(int id) {
		dao.excluir(id);
		//suspenderVoluntario(idVoluntario);
	}
	
	public void suspenderVoluntario(Integer idVoluntario) {
		dao.suspenderVoluntario(idVoluntario);
	}

	public Voluntario carregar(int id) {
		return dao.carregar(id);
	}
	
	public Integer salvar(Voluntario voluntario) {
		if (voluntario.getId() == null) {
			Endereco endereco = enderecoService.salvar(voluntario.getEndereco());
			voluntario.setEndereco(endereco);

			Integer idVoluntario = dao.inserir(voluntario);

			return idVoluntario;
		}
		
		enderecoService.salvar(voluntario.getEndereco());
		dao.atualizar(voluntario);
		
		return voluntario.getId();		
	}
	/*
	public Voluntario consultar(Integer idVoluntario) {
		Voluntario voluntario = dao.findById(idVoluntario);

		return voluntario;
	}
	*/
	
	public List<Voluntario> consultarFiltro(String nome, String cpf) {
		List<Voluntario> listaVoluntarios = dao.consultarFiltro(nome, cpf);
		return listaVoluntarios;
	}
	
	public Voluntario consultarDetalhes(Integer idVoluntario) {
		Voluntario voluntario = dao.carregar(idVoluntario);

		List<Projeto> projetos = projetoService.consultarDetalhesVol(idVoluntario);
		voluntario.setProjetos(projetos);

		return voluntario;
	}

	public List<Voluntario> voluntariosCompativeis(Integer idProjeto) {
		Projeto projeto = projetoService.consultar(idProjeto);
		
		List<Voluntario> voluntarios = dao.voluntariosCompativeis(projeto);
		
		int qtd = projeto.getQtdVoluntarios() - projetoService.countVoluntarios(projeto.getId());
		if(qtd< voluntarios.size()){
			voluntarios = busca(qtd,voluntarios);
		}
		return voluntarios;
	}

	private List<Voluntario> busca(Integer qtdVoluntarios, List<Voluntario> voluntarios) {
		for(int i = qtdVoluntarios; i < voluntarios.size(); i++){
			voluntarios.remove(i);
		}
		return voluntarios;
	}

	public List<Voluntario> voluntariosAssociados(Integer idProjeto) {
		
		return dao.porIdProjeto(idProjeto);
	}

	public String cadastraVoluntario(Integer idProjeto, Integer idVoluntario) {
		return dao.cadastraVoluntario(idProjeto, idVoluntario);
	}
}
