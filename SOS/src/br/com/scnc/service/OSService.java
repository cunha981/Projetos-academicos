package br.com.scnc.service;

import java.util.List;

import br.com.scnc.bean.Endereco;
import br.com.scnc.bean.OrganizacaoSocial;
import br.com.scnc.bean.Projeto;
import br.com.scnc.dao.OSDAO;

public class OSService {

	private OSDAO osDAO = new OSDAO();

	private EnderecoService enderecoService = new EnderecoService();

	private ProjetoService projetoService = new ProjetoService();

	public Integer salvar(OrganizacaoSocial os) {
		if (os.getId() == null) {
			Endereco endereco = enderecoService.salvar(os.getEndereco());
			os.setEndereco(endereco);

			Integer idOS = osDAO.insert(os);

			return idOS;
		}
		enderecoService.salvar(os.getEndereco());
		osDAO.atualizar(os);
		
		return os.getId();
	}

	public List<OrganizacaoSocial> consultarFiltro(String nome, String cnpj) {
		// OrganizacaoSocial os = osDAO.findById(2);
		List<OrganizacaoSocial> listaOS = osDAO.consultaFiltro(nome, cnpj);
		return listaOS;
	}

	public OrganizacaoSocial consultarDetalhes(Integer idOS) {
		OrganizacaoSocial os = osDAO.findById(idOS);

		List<Projeto> projetos = projetoService.consultarDetalhes(idOS);
		os.setProjetos(projetos);

		return os;
	}

	public OrganizacaoSocial consultar(Integer idOS) {
		OrganizacaoSocial os = osDAO.findById(idOS);
		return os;
	}

	public void excluir(Integer idOS) {
		osDAO.excluir(idOS);
		
		projetoService.suspendeProjeto(idOS);
	}

	public List<OrganizacaoSocial> listaOSVoluntario(Integer idVoluntario) {
		return osDAO.listaOSVoluntario(idVoluntario);
	}

}
